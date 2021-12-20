package com.gustavoboliveira.desafiotecnico.dataanalysissystem.models;

import java.math.BigDecimal;

public class Salesman {

    private Long cpf;
    private String name;
    private BigDecimal salary;
    private BigDecimal totalSold;

    public Salesman() {
        this.totalSold = BigDecimal.ZERO;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public BigDecimal getTotalSold() {
        return totalSold;
    }

    public void setTotalSold(BigDecimal amountSold) {
        this.totalSold = this.totalSold.add(amountSold);
    }
}
