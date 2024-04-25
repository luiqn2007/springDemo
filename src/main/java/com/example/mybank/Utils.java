package com.example.mybank;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Utils {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    public static String formatDate(Date date) {
        return DATE_FORMAT.format(date);
    }

    public static String formatErrors(Errors errors, String filedName) {
        List<FieldError> fieldErrors = errors.getFieldErrors(filedName);
        FieldError lastError = fieldErrors.get(fieldErrors.size() - 1);
        return lastError.getDefaultMessage();
    }
}
