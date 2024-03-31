package com.example.mybank;

import com.example.mybank.spel.SpELSample;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SpELTest {

    @Test
    void testSpel() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spel_test.xml");
        SpELSample sample = (SpELSample) context.getBean("sample");

        assertEquals("Some currency", sample.getCurrency());
        assertSame(sample.getEnv2(), sample.getEnv());
        assertEquals("test", sample.getEnvironment());
        assertEquals("A city", sample.getCity());
        assertArrayEquals(new String[]{"AAA", "bbb"}, sample.getSplitName());
        assertTrue(sample.isGreaterThan());
        assertTrue(sample.isConditionTrue());
        assertEquals(2, sample.getTotalAmount());
        assertTrue(sample.isEmail());
//        assertEquals("A simple String value in list", sample.getListItem());
//        assertEquals("value2", sample.getMapItem());
    }
}
