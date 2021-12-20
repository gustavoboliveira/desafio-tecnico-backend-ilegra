package com.gustavoboliveira.desafiotecnico.dataanalysissystem.builders;

import com.gustavoboliveira.desafiotecnico.dataanalysissystem.models.Item;
import com.gustavoboliveira.desafiotecnico.dataanalysissystem.models.Sale;
import com.gustavoboliveira.desafiotecnico.dataanalysissystem.models.Salesman;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BuilderSale {

    private Sale sale;

    public BuilderSale() {
        this.sale = new Sale();
    }

    public static BuilderSale builder() {
        return new BuilderSale();
    }

    public BuilderSale withId(String id) {
        this.sale.setId(new AtomicInteger(Integer.parseInt(id)));
        return this;
    }

    public BuilderSale withListItems(String items) {
        List<Item> itemsList = new ArrayList<>();
        String[] itemsTemporary = items.split(",");

        for (String item : itemsTemporary) {
            String[] elementsItem = item.split("-");

            AtomicInteger id = new AtomicInteger(
                    Integer.parseInt(elementsItem[0]));

            int quantity = Integer.parseInt(elementsItem[1]);

            BigDecimal price = BigDecimal.valueOf(
                    Double.parseDouble(elementsItem[2]));

            itemsList.add(BuilderItem.builder()
                    .withId(id)
                    .withQuantity(quantity)
                    .withPrice(price)
                    .build());

            this.sale.setTotal(
                    price.multiply(BigDecimal.valueOf(quantity)));
        }
        this.sale.setItems(itemsList);
        return this;
    }

    public BuilderSale withSalesmanName(String name, List<Salesman> salesmanList) {
        this.sale.setSalesmanName(name);

        Salesman salesman = salesmanList.stream()
                .filter(s -> s.getName().equalsIgnoreCase(name))
                .findFirst()
                .get();

        salesman.setTotalSold(this.sale.getTotal());
        return this;
    }

    public Sale build() {
        return this.sale;
    }
}
