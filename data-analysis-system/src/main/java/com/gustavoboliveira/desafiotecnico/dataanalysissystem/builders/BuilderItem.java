package com.gustavoboliveira.desafiotecnico.dataanalysissystem.builders;

import com.gustavoboliveira.desafiotecnico.dataanalysissystem.models.Item;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

public class BuilderItem {

    private Item item;

    public BuilderItem() {
        this.item = new Item();
    }

    public static BuilderItem builder(){
        return new BuilderItem();
    }

    public BuilderItem withId(AtomicInteger id){
        this.item.setId(id);
        return this;
    }

    public BuilderItem withQuantity(int quantity){
        this.item.setQuantity(quantity);
        return this;
    }

    public BuilderItem withPrice(BigDecimal price){
        this.item.setPrice(price);
        return this;
    }

    public Item build(){
        return this.item;
    }
}