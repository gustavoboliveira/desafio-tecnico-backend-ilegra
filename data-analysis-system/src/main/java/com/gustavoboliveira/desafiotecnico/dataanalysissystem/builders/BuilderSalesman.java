package com.gustavoboliveira.desafiotecnico.dataanalysissystem.builders;

import com.gustavoboliveira.desafiotecnico.dataanalysissystem.models.Salesman;

import java.math.BigDecimal;

public class BuilderSalesman {

    private Salesman salesman;

    public BuilderSalesman() {
        this.salesman = new Salesman();
    }

    public static BuilderSalesman builder(){
        return new BuilderSalesman();
    }

    public BuilderSalesman withCPF(String cpf){
        this.salesman.setCpf(Long.parseLong(cpf));
        return this;
    }

    public BuilderSalesman withName(String name){
        this.salesman.setName(name);
        return this;
    }

    public BuilderSalesman withSalary(String salary){
        this.salesman.setSalary(BigDecimal.valueOf(Double.parseDouble(salary)));
        return this;
    }

    public Salesman build(){
        return this.salesman;
    }
}
