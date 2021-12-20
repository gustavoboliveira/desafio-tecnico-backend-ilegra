package com.gustavoboliveira.desafiotecnico.dataanalysissystem;

import com.gustavoboliveira.desafiotecnico.dataanalysissystem.builders.BuilderSalesman;
import com.gustavoboliveira.desafiotecnico.dataanalysissystem.models.Salesman;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class BuilderSalesmanTests {

    @Test
    void shouldReturnSalesmanSuccessTest() {
        assertTrue(BuilderSalesman.builder()
                .withCPF("12345678912")
                .withName("Joao")
                .withSalary("40000.99")
                .build()
                .getClass()
                .equals(Salesman.class));
    }

    @Test
    void shouldThrowNumberFormatExceptionWhenInformedLetterInCPFTest() {
        assertThrows(NumberFormatException.class,
                () -> BuilderSalesman.builder()
                        .withCPF("1234564a")
                        .withName("Joao")
                        .withSalary("40000.99")
                        .build());
    }

    @Test
    void shouldThrowNumberFormatExceptionWhenInformedLetterInSalaryTest() {
        assertThrows(NumberFormatException.class,
                () -> BuilderSalesman.builder()
                        .withCPF("12345678912")
                        .withName("Joao")
                        .withSalary("40a000.99")
                        .build());
    }
}
