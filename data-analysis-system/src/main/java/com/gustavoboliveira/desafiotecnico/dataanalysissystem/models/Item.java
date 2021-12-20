package com.gustavoboliveira.desafiotecnico.dataanalysissystem.models;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

public class Item {

    private AtomicInteger id;
    private int quantity;
    private BigDecimal price;

    public Item() {}

    public AtomicInteger getId() {
        return id;
    }

    public void setId(AtomicInteger id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}