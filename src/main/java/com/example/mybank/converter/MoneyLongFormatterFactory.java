package com.example.mybank.converter;

import com.example.mybank.annotation.MoneyFormatter;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.util.Set;

public class MoneyLongFormatterFactory implements AnnotationFormatterFactory<MoneyFormatter> {

    private final MoneyLongValueFormatter formatter = new MoneyLongValueFormatter();

    @Override
    public Set<Class<?>> getFieldTypes() {
        return Set.of(Long.class);
    }

    @Override
    public Printer<?> getPrinter(MoneyFormatter annotation, Class<?> fieldType) {
        return formatter;
    }

    @Override
    public Parser<?> getParser(MoneyFormatter annotation, Class<?> fieldType) {
        return formatter;
    }
}
