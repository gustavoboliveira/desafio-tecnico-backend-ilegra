package com.gustavoboliveira.desafiotecnico.dataanalysissystem.services;

import com.gustavoboliveira.desafiotecnico.dataanalysissystem.models.Sale;
import com.gustavoboliveira.desafiotecnico.dataanalysissystem.models.Salesman;
import com.gustavoboliveira.desafiotecnico.dataanalysissystem.repositories.SystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ReportService {

    @Autowired
    private SystemRepository repository;

    public int amountClients() {
        return repository.getCustomerList().size();
    }

    public int amountSalesman() {
        return repository.getSalesmanList().size();
    }

    public AtomicInteger idMostSaleExpensive() {
        if(repository.getSaleList().isEmpty())
            return new AtomicInteger(-1);
        return repository.getSaleList().stream().max(Comparator.comparing(Sale::getTotal)).get().getId();
    }

    public Salesman worstSalesman() {
        if (repository.getSalesmanList().isEmpty())
            return null;
        return repository.getSalesmanList().stream().min(Comparator.comparing(Salesman::getTotalSold)).get();
    }
}
