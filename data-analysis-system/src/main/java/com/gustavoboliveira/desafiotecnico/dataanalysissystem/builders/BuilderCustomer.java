package com.gustavoboliveira.desafiotecnico.dataanalysissystem.builders;

import com.gustavoboliveira.desafiotecnico.dataanalysissystem.models.Customer;

public class BuilderCustomer {

    private Customer customer;

    public BuilderCustomer() {
        this.customer = new Customer();
    }

    public static BuilderCustomer builder(){
        return new BuilderCustomer();
    }

    public BuilderCustomer withCNPJ(String cnpj){
        this.customer.setCnpj(Long.parseLong(cnpj));
        return this;
    }

    public BuilderCustomer withName(String name){
        this.customer.setName(name);
        return this;
    }

    public BuilderCustomer withBusinessArea(String businessArea){
        this.customer.setBusinessArea(businessArea);
        return this;
    }

    public Customer build(){
        return this.customer;
    }
}