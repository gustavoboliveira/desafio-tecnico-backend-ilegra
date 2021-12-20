package com.gustavoboliveira.desafiotecnico.dataanalysissystem;

import com.gustavoboliveira.desafiotecnico.dataanalysissystem.builders.BuilderCustomer;
import com.gustavoboliveira.desafiotecnico.dataanalysissystem.models.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class BuilderCustomerTests {

    @Test
    void shouldReturnCustomerSuccessTest() {
        assertTrue(BuilderCustomer.builder()
                .withCNPJ("123456789123456")
                .withName("Joao")
                .withBusinessArea("Rural")
                .build()
                .getClass()
                .equals(Customer.class));
    }

    @Test
    void shouldThrowNumberFormatExceptionWhenInformedLetterInCNPJTest() {
        assertThrows(NumberFormatException.class,
                () -> BuilderCustomer.builder()
                        .withCNPJ("1234564a4567")
                        .withName("Joao")
                        .withBusinessArea("Rural")
                        .build());
    }
}
