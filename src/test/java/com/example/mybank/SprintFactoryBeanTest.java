package com.example.mybank;

import com.example.mybank.bean.ExampleFactoryBean;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@SpringBootTest
public class SprintFactoryBeanTest {

    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("util_mode_test.xml");
        assertEquals("hello world", context.getBean("example-factory"));
        assertEquals(new ExampleFactoryBean(), context.getBean("&example-factory"));
        assertSame(context.getBean("example-factory"), context.getBean("factory-value"));
        assertSame(context.getBean("&example-factory"), context.getBean("factory-bean"));
    }
}
