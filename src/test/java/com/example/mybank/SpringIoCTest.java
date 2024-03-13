package com.example.mybank;

import com.example.mybank.chapter02.controller.FixedDepositControllerV2;
import com.example.mybank.chapter02.dao.FixedDepositDao;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SpringIoCTest {

    private static ApplicationContext context;

    @BeforeAll
    public static void init() {
        context = new ClassPathXmlApplicationContext("cp02_applicationContext_test.xml");
    }

    /**
     * <ul>
     *     <li>默认作用域 singleton</li>
     *     <li>多次调用 ApplicationContext 的 getBean 返回相同的对象</li>
     * </ul>
     */
    @Test
    void testInstances() {
        FixedDepositControllerV2 controller1 = (FixedDepositControllerV2) context.getBean("container");
        FixedDepositControllerV2 controller2 = (FixedDepositControllerV2) context.getBean("container");
        assertSame(controller1, controller2, "Different FixedDepositController instance");
    }

    /**
     * 同一个 ApplicationContext 中，singleton 作用域对象 Xml 使用的与直接获取的是同一个
     */
    @Test
    void testReference() {
        FixedDepositControllerV2 controller = (FixedDepositControllerV2) context.getBean("container");

        FixedDepositDao dao1 = controller.getFixedDepositService().getFixedDepositDao();
        FixedDepositDao dao2 = (FixedDepositDao) context.getBean("dao");
        assertSame(dao1, dao2, "Different FixedDepositDao reference");
    }

    /**
     * 不同 ApplicationContext 中，singleton 作用域对象不同
     */
    @Test
    void testSingletonScope() {
        ApplicationContext anotherContext = new ClassPathXmlApplicationContext("cp02_applicationContext_test.xml");
        FixedDepositControllerV2 controller1 = (FixedDepositControllerV2) context.getBean("container");
        FixedDepositControllerV2 controller2 = (FixedDepositControllerV2) anotherContext.getBean("container");
        assertNotSame(controller1, controller2, "Same FixedDepositController instance");
    }

    /**
     * 同一 ApplicationContext 中，两个同类型 singleton 作用域对象不同
     */
    @Test
    void testSingletonScopePerBeanDef() {
        FixedDepositDao dao1 = (FixedDepositDao) context.getBean("dao");
        FixedDepositDao dao2 = (FixedDepositDao) context.getBean("anotherDao");
        assertNotSame(dao1, dao2, "Same FixedDepositDao instance");
    }

    /**
     * 延迟初始化 ABean 导致异常
     */
    @Test
    void testLazyInit() {
        assertDoesNotThrow(() -> context.getBean("bBean"), "BBean throws exception");
        assertThrows(BeanCreationException.class, () -> context.getBean("aBean"), "ABean not throw exception");
    }

    /**
     * 作用域 property 每次提取创建新对象
     */
    @Test
    void testPropertyInstances() {
        FixedDepositDetails detail1 = (FixedDepositDetails) context.getBean("fixedDepositDetails");
        FixedDepositDetails detail2 = (FixedDepositDetails) context.getBean("fixedDepositDetails");
        assertNotSame(detail1, detail2, "Same FixedDepositDetails instances");
    }
}
