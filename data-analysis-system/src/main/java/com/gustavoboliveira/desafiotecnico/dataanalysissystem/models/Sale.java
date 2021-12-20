package com.gustavoboliveira.desafiotecnico.dataanalysissystem.models;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Sale {

    private AtomicInteger id;
    private List<Item> items;
    private String salesmanName;
    private BigDecimal total;

    public Sale() {
        this.total = BigDecimal.ZERO;
    }

    public AtomicInteger getId() {
        return id;
    }

    public void setId(AtomicInteger id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal item) {
        this.total = this.total.add(item);
    }
}
