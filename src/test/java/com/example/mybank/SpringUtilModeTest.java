package com.example.mybank;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SpringUtilModeTest {

    @Test
    void testUtilMode() {
        ApplicationContext context = new ClassPathXmlApplicationContext("util_mode_test.xml");
        assertEquals(List.of("A simple String value in list", "Another simple String value in list"), context.getBean("listType"));
        assertSame(LinkedList.class, context.getBean("listType").getClass());
        assertEquals(Map.of("key1", "value1", "key2", "value2"), context.getBean("mapType"));
        assertSame(HashMap.class, context.getBean("mapType").getClass());
        assertEquals(Set.of("Element 1", "Element 2"), context.getBean("setType"));
        assertSame(HashSet.class, context.getBean("setType").getClass());
        assertSame(context.getBean("listType").getClass(), context.getBean("dataType"));
        assertTrue((boolean) context.getBean("bool_true"));
    }
}
