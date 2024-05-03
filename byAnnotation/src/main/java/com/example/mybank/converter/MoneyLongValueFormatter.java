package com.example.mybank.converter;

import jakarta.jms.MessageFormatException;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Objects;

@Component
public class MoneyLongValueFormatter implements Formatter<Long> {
    @Override
    public Long parse(String text, Locale locale) {
        return Long.valueOf(text.split(" ")[0]);
    }

    @Override
    public String print(Long object, Locale locale) {
        if (Objects.equals(locale.getLanguage(), new Locale("de").getLanguage())) {
            return String.format(locale, "%d EURO", object);
        }
        if (Objects.equals(locale.getLanguage(), new Locale("en").getLanguage())) {
            return String.format(locale, "%d USD", object);
        }
        if (Objects.equals(locale.getLanguage(), new Locale("zh").getLanguage())) {
            return String.format(locale, "%d RMB", object);
        }
        throw new RuntimeException(new MessageFormatException("Unsupported locale " + locale));
    }
}
