package com.example.mybank_xml.converter;

import com.example.mybank_xml.annotation.MoneyFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class MoneyLongFormatterFactory implements AnnotationFormatterFactory<MoneyFormatter> {

    @Autowired
    private MoneyLongValueFormatter moneyLongValueFormatter;

    @Override
    public Set<Class<?>> getFieldTypes() {
        return Set.of(Long.class, long.class);
    }

    @Override
    public Printer<?> getPrinter(MoneyFormatter annotation, Class<?> fieldType) {
        return moneyLongValueFormatter;
    }

    @Override
    public Parser<?> getParser(MoneyFormatter annotation, Class<?> fieldType) {
        return moneyLongValueFormatter;
    }
}
