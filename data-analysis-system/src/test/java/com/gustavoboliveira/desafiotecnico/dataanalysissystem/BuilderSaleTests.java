package com.gustavoboliveira.desafiotecnico.dataanalysissystem;

import com.gustavoboliveira.desafiotecnico.dataanalysissystem.builders.BuilderSale;
import com.gustavoboliveira.desafiotecnico.dataanalysissystem.builders.BuilderSalesman;
import com.gustavoboliveira.desafiotecnico.dataanalysissystem.models.Sale;
import com.gustavoboliveira.desafiotecnico.dataanalysissystem.models.Salesman;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class BuilderSaleTests {

    private List<Salesman> salemanList;
    private String items;

    @BeforeEach
    void init() {
        salemanList = new ArrayList<>();
        salemanList.add(BuilderSalesman.builder()
                .withCPF("12345678912")
                .withName("Joao")
                .withSalary("40000.99")
                .build());
        items = "1-10-100,2-30-2.50,3-40-3.10";
    }

    @Test
    void shouldReturnItemSuccessTest() {
        assertTrue(BuilderSale.builder().withId("2")
                .withListItems(items)
                .withSalesmanName("Joao", salemanList)
                .build()
                .getClass()
                .equals(Sale.class));
    }

    @Test
    void shouldThrowNumberFormatExceptionWhenInformedLetterInIdTest() {
        assertThrows(NumberFormatException.class,
                () -> BuilderSale.builder()
                        .withId("a")
                        .withListItems(items)
                        .withSalesmanName("Joao", salemanList)
                        .build());
    }

    @Test
    void shouldThrowNumberFormatExceptionWhenInformedLetterInItemIdTest() {
        items = "a-10-100";
        assertThrows(NumberFormatException.class,
                () -> BuilderSale.builder()
                        .withId("2")
                        .withListItems(items)
                        .withSalesmanName("Joao", salemanList)
                        .build());
    }

    @Test
    void shouldThrowNumberFormatExceptionWhenInformedLetterInItemQuantityTest() {
        items = "1-1a-100";
        assertThrows(NumberFormatException.class,
                () -> BuilderSale.builder()
                        .withId("2")
                        .withListItems(items)
                        .withSalesmanName("Joao", salemanList)
                        .build());
    }

    @Test
    void shouldThrowNumberFormatExceptionWhenInformedLetterInItemPriceTest() {
        items = "1-10-10a";
        assertThrows(NumberFormatException.class,
                () -> BuilderSale.builder()
                        .withId("2")
                        .withListItems(items)
                        .withSalesmanName("Joao", salemanList)
                        .build());
    }
}
