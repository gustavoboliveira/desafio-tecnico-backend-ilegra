package com.gustavoboliveira.desafiotecnico.dataanalysissystem;

import com.gustavoboliveira.desafiotecnico.dataanalysissystem.builders.BuilderItem;
import com.gustavoboliveira.desafiotecnico.dataanalysissystem.models.Item;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class BuilderItemTest {

    @Test
    void shouldReturnItemSuccessTest() {
        assertTrue(BuilderItem.builder()
                .withId(new AtomicInteger(2))
                .withQuantity(10)
                .withPrice(new BigDecimal(2.50))
                .build()
                .getClass()
                .equals(Item.class));
    }
}
