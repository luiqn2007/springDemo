package com.example.mybank;

import com.example.mybank.spel.Simple;
import com.example.mybank.spel.SpringUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SpELTest {

    private static ExpressionParser parser;
    private Expression exp;

    @BeforeAll
    static void init() {
        parser = new SpelExpressionParser();
    }

    @Test
    public void testLiteral() {
        exp = parser.parseExpression("'Hello World'");
        assertEquals("Hello World", exp.getValue(String.class));

        exp = parser.parseExpression("null");
        assertNull(exp.getValue());
    }

    @Test
    public void testReference() {
        exp = parser.parseExpression("'Hello World'.bytes");
        assertArrayEquals("Hello World".getBytes(), exp.getValue(byte[].class));

        exp = parser.parseExpression("'Hello World'.bytes.length");
        assertEquals("Hello World".getBytes().length, exp.getValue());

        exp = parser.parseExpression("'Hello World'.bytes.length");
        assertEquals("Hello World".getBytes().length, exp.getValue());

        exp = parser.parseExpression("'Hello World'.length()");
        assertEquals("Hello World".length(), exp.getValue(int.class));

        exp = parser.parseExpression("'Hello World'.charAt(3)");
        assertEquals('l', exp.getValue(char.class));

        exp = parser.parseExpression("{1,2,3,4,5}");
        System.out.println(exp.getValueType());
        assertEquals(List.of(1, 2, 3, 4, 5), exp.getValue(List.class));
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, exp.getValue(int[].class));

        exp = parser.parseExpression("{}");
        System.out.println(exp.getValueType());
    }

    @Test
    public void testIndexer() {
        int[] array = new int[]{0, 1, 2, 3, 4, 5};
        exp = parser.parseExpression("[3]");
        assertEquals(3, exp.getValue(array));

        List<String> list = List.of("a", "b", "c", "d", "e");
        exp = parser.parseExpression("[3]");
        assertEquals("d", exp.getValue(list));

        Map<String, String> map = Map.of("a", "A", "b", "B", "c", "C", "d", "D");
        exp = parser.parseExpression("['d']");
        assertEquals("D", exp.getValue(map));

        Properties properties = new Properties();
        properties.put("a", "A");
        properties.put("b", "B");
        properties.put("c", "C");
        exp = parser.parseExpression("['c']");
        assertEquals("C", exp.getValue(properties));
    }

    @Test
    public void testContext() throws NoSuchMethodException, IllegalAccessException {
        Calendar calendar = Calendar.getInstance();
        StandardEvaluationContext context = new StandardEvaluationContext(calendar);
        exp = parser.parseExpression("time");
        assertEquals(calendar.getTime(), exp.getValue(context));

        exp = parser.parseExpression("#root.time");
        assertEquals(calendar.getTime(), exp.getValue(context));

        context = new StandardEvaluationContext();
        context.setVariable("calendar", calendar);
        exp = parser.parseExpression("#calendar.time");
        assertEquals(calendar.getTime(), exp.getValue(context));

        context = new StandardEvaluationContext();
        MethodHandle reverse = MethodHandles.lookup().
                findStatic(SpringUtil.class, "reverseString",
                        MethodType.methodType(String.class, String.class));
        context.registerFunction("reverse", reverse);
        exp = parser.parseExpression("#reverse('abc')");
        assertEquals("cba", exp.getValue(context));

        Simple simple = new Simple();
        simple.booleanList.add(true);
        StandardEvaluationContext simpleContext = new StandardEvaluationContext(simple);
        exp = parser.parseExpression("booleanList[0]");
        exp.setValue(simpleContext, false);
        Assertions.assertFalse(simple.booleanList.get(0));
    }

    @Test
    public void testRelationalOperation() {
        exp = parser.parseExpression("'abc' < 'ad'");
        assertTrue(exp.getValue(boolean.class));

        exp = parser.parseExpression("100 > null");
        assertTrue(exp.getValue(boolean.class));

        exp = parser.parseExpression("'abc' instanceof T(int)");
        assertFalse(exp.getValue(boolean.class));

        exp = parser.parseExpression("'abc' instanceof T(String)");
        assertTrue(exp.getValue(boolean.class));

        exp = parser.parseExpression("'5.00' matches '^-?\\d+(\\.\\d{2})?$'");
        assertTrue(exp.getValue(boolean.class));

        exp = parser.parseExpression("'5.0067' matches '^-?\\d+(\\.\\d{2})?$'");
        assertFalse(exp.getValue(boolean.class));
    }

    @Test
    public void testLogicalOperation() {
        exp = parser.parseExpression("'abc' == 'abcd' and 'abc' == 'abc'");
        assertFalse(exp.getValue(boolean.class));
        exp = parser.parseExpression("'abc' == 'abcd' && 'abc' == 'abc'");
        assertFalse(exp.getValue(boolean.class));

        exp = parser.parseExpression("'abc' == 'abcd' or 'abc' == 'abc'");
        assertTrue(exp.getValue(boolean.class));
        exp = parser.parseExpression("'abc' == 'abcd' || 'abc' == 'abc'");
        assertTrue(exp.getValue(boolean.class));
    }

    @Test
    public void testTypeOperation() {
        exp = parser.parseExpression("T(String)");
        assertEquals(String.class, exp.getValue());

        exp = parser.parseExpression("T(Boolean).TRUE");
        assertEquals(Boolean.TRUE, exp.getValue());
    }
}
