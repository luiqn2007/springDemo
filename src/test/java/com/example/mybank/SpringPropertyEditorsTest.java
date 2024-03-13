package com.example.mybank;

import com.example.mybank.bean.DataTypesExample;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SpringPropertyEditorsTest {

    private static ApplicationContext context;

    @BeforeAll
    public static void init() {
        context = new ClassPathXmlApplicationContext("property_editors_test.xml");
    }

    private Properties buildProperty(Object... keyAndValues) {
        Properties properties = new Properties();
        for (int i = 0; i + 1 < keyAndValues.length; i += 2) {
            properties.put(keyAndValues[i], keyAndValues[i + 1]);
        }
        return properties;
    }

    @Test
    void testInnerPropertyEditor() {
        try {
            DataTypesExample example = context.getBean(DataTypesExample.class);
            assertEquals("My Person Bank", example.getStringType());
            assertArrayEquals("Retail banking".getBytes(), example.getByteArray());
            assertArrayEquals("Address of head office".toCharArray(), example.getCharArray());
            assertEquals('y', example.getCharType());
            assertSame(example.getClass(), example.getClassType());
            assertEquals(Currency.getInstance("INR"), example.getCurrencyType());
            assertTrue(example.getBooleanType());
            assertEquals(new SimpleDateFormat("dd-MM-yyyy").parse("30-01-2012"), example.getDateType());
            assertEquals(1000000000, example.getLongType());
            assertEquals(1.0000000001, example.getDoubleType());
            assertEquals(buildProperty("x", "Branch X's address",
                    "y", "Branch Y's address"), example.getPropertiesType());
            assertArrayEquals(new int[]{1, 2, 3}, example.getArrayType());
            assertNull(example.getNullType());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testUtilMode() {
        DataTypesExample example = context.getBean(DataTypesExample.class);
        assertEquals(List.of("list element 1", "list element 2"), example.getListType());
        assertEquals(Set.of(1, 2, 3), example.getSetType());
        assertEquals(Map.of("mapValue1", context.getBean("mapValue1"),
                "mapValue2", context.getBean("mapValue2"),
                "mapValue3", context.getBean("mapValue3")), example.getMapType());
        assertEquals(buildProperty("book", "Getting started with the Spring Framework"), example.getAnotherPropertiesType());
    }
}
