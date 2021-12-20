package com.gustavoboliveira.desafiotecnico.dataanalysissystem.services;

import com.gustavoboliveira.desafiotecnico.dataanalysissystem.DataAnalysisSystemApplication;
import com.gustavoboliveira.desafiotecnico.dataanalysissystem.repositories.SystemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SystemService {

    @Value("${salesman.code}")
    private String salesmanCode;

    @Value("${customer.code}")
    private String customerCode;

    @Value("${sales.code}")
    private String salesCode;

    @Value("${delimiter}")
    private String delimiter;

    @Autowired
    private SystemRepository repository;

    @Autowired
    private ReportService reportService;

    private static Logger logger = LoggerFactory.getLogger(DataAnalysisSystemApplication.class);

    public boolean buildReport() {
        List<String> data = repository.readFiles();

        if (data.isEmpty())
            return false;

        while (!data.isEmpty()) {
            List<String> temp = new ArrayList<>(data);
            for (String d : temp) {
                if (!buildObjects(d))
                    logger.error("Failed to process data");
                else
                    data.remove(d);
            }
        }

        if (repository.writeFile(generateReportData())) {
            logger.info("Report generated successfully");
            return true;
        }

        return false;
    }

    private boolean buildObjects(String data) {
        String[] elements = data.split(delimiter);
        if (data.startsWith(salesmanCode)) {
            return repository.buildSalesman(elements);
        } else if (data.startsWith(customerCode)) {
            return repository.buildCustomer(elements);
        } else if (data.startsWith(salesCode)) {
            if (repository.getSalesmanList().isEmpty() || repository.getCustomerList().isEmpty())
                return false;
            return repository.buildSale(elements);
        }
        return false;
    }

    private String generateReportData() {
        int amountClients = reportService.amountClients();
        int amountSalesman = reportService.amountSalesman();
        String idMostSaleExpensive;
        String worstSaleman;

        if (reportService.idMostSaleExpensive().intValue() == -1)
            idMostSaleExpensive = "No sales registered";
        else
            idMostSaleExpensive = reportService.idMostSaleExpensive().toString();

        if (reportService.worstSalesman() == null)
            worstSaleman = "No salesman registred";
        else
            worstSaleman = "{Name: " + reportService.worstSalesman().getName() +
                    "; CPF: " + reportService.worstSalesman().getCpf() + "}";

        return "Amount of clients: " + amountClients +
                "\nAmount of salesman: " + amountSalesman +
                "\nMost expensive sale (id): " + idMostSaleExpensive +
                "\nWorst salesman: " + worstSaleman;
    }
}
