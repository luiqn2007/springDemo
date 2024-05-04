package com.example.mybank_xml.util;

import com.example.mybank_xml.converter.MoneyLongValueFormatter;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Component
public class Utils {

    public static Utils INSTANCE;

    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    @PostConstruct
    private void postConstruct() {
        INSTANCE = this;
    }

    @Autowired
    private MoneyLongValueFormatter moneyLongValueFormatter;

    public String formatDate(Date date) {
        return DATE_FORMAT.format(date);
    }

    public String formatErrors(Errors errors, String filedName) {
        List<FieldError> fieldErrors = errors.getFieldErrors(filedName);
        FieldError lastError = fieldErrors.get(fieldErrors.size() - 1);
        return lastError.getDefaultMessage();
    }

    public String formatMoney(long value, Locale locale) {
        return moneyLongValueFormatter.print(value, locale);
    }
}
