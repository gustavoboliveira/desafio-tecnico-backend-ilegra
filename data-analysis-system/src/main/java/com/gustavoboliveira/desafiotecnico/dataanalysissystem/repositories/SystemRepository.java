package com.gustavoboliveira.desafiotecnico.dataanalysissystem.repositories;

import com.gustavoboliveira.desafiotecnico.dataanalysissystem.DataAnalysisSystemApplication;
import com.gustavoboliveira.desafiotecnico.dataanalysissystem.builders.BuilderCustomer;
import com.gustavoboliveira.desafiotecnico.dataanalysissystem.builders.BuilderSale;
import com.gustavoboliveira.desafiotecnico.dataanalysissystem.builders.BuilderSalesman;
import com.gustavoboliveira.desafiotecnico.dataanalysissystem.models.Customer;
import com.gustavoboliveira.desafiotecnico.dataanalysissystem.models.Sale;
import com.gustavoboliveira.desafiotecnico.dataanalysissystem.models.Salesman;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class SystemRepository {

    @Value("${salesman.code}")
    private String salesmanCode;

    @Value("${customer.code}")
    private String customerCode;

    @Value("${sales.code}")
    private String salesCode;

    @Value("${input.file.extension}")
    private String inputExtension;

    @Value("${input.directory}")
    private String inputDirectory;

    @Value("${output.directory}")
    private String outputDirectory;

    @Value("${output.file}")
    private String outputFile;

    private final List<Salesman> salesmanList = new ArrayList<>();
    private final List<Customer> customerList = new ArrayList<>();
    private final List<Sale> saleList = new ArrayList<>();

    private static Logger logger = LoggerFactory.getLogger(DataAnalysisSystemApplication.class);

    public boolean buildSalesman(String[] elements) {
        try {
            salesmanList.add(BuilderSalesman.builder()
                    .withCPF(elements[1])
                    .withName(elements[2])
                    .withSalary(elements[3])
                    .build());
            logger.info("Processed salesman data");
            return true;
        } catch (NumberFormatException e) {
            logger.error("Can't processed salesman data");
        }
        return false;
    }

    public boolean buildCustomer(String[] elements) {
        try {
            customerList.add(BuilderCustomer.builder()
                    .withCNPJ(elements[1])
                    .withName(elements[2])
                    .withBusinessArea(elements[3])
                    .build());
            logger.info("Processed customer data");
            return true;
        } catch (NumberFormatException e) {
            logger.error("Can't processed customer data");
        }
        return false;
    }

    public boolean buildSale(String[] elements) {
        try {
            String items = elements[2].replace("[", "")
                    .replace("]", "");
            saleList.add(BuilderSale.builder()
                    .withId(elements[1])
                    .withListItems(items)
                    .withSalesmanName(elements[3], salesmanList)
                    .build());
            logger.info("Processed sales data");
            return true;
        } catch (NumberFormatException e) {
            logger.error("Can't processed sale data");
        }
        return false;
    }

    public List<Salesman> getSalesmanList() {
        return salesmanList;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public List<Sale> getSaleList() {
        return saleList;
    }

    public List<String> readFiles() {
        try (Stream<Path> walk = Files.walk(Paths.get(inputDirectory))) {
            List<Path> pathList = walk.filter(f -> f.toString().endsWith(inputExtension)).collect(Collectors.toList());
            if (!pathList.isEmpty()) {
                List<String> dataList = new ArrayList<>();
                for (Path p : pathList) {
                    Scanner sc = new Scanner(p.toFile());
                    String temp = sc.next();
                    String data = "";
                    while (Objects.nonNull(temp)) {
                        try {
                            if (temp.startsWith(salesmanCode) || temp.startsWith(customerCode) || temp.startsWith(salesCode)) {
                                data = temp;
                                temp = sc.next();
                            }
                            while (!temp.startsWith(salesmanCode) && !temp.startsWith(customerCode) && !temp.startsWith(salesCode)) {
                                data = data.concat(" " + temp);
                                if (sc.hasNext())
                                    temp = sc.next();
                            }
                        } catch (NoSuchElementException e) {
                            temp = null;
                        }
                        dataList.add(data);
                    }
                    sc.close();
                    logger.info("Read data from file " + p.getFileName());
                }
                return dataList;
            }
        } catch (IOException | NoSuchElementException e) {
            logger.error("Can't read file in directory " + inputDirectory);
        }
        return Collections.emptyList();
    }

    public boolean writeFile(String report) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputDirectory.concat(outputFile)))) {
            bw.write(report);
            bw.close();
            logger.info("Generated file");
            return true;
        } catch (IOException e) {
            logger.error("Can't write file in directory " + outputDirectory);
        }
        return false;
    }
}