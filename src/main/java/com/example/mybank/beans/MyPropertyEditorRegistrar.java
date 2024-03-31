package com.example.mybank.beans;

import jakarta.inject.Named;
import jakarta.inject.Singleton;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.propertyeditors.CustomDateEditor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Named
@Singleton
public class MyPropertyEditorRegistrar implements PropertyEditorRegistrar {

    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {
        CustomDateEditor dateEditor = new CustomDateEditor(new SimpleDateFormat("dd-MM-yyyy"), false);
        registry.registerCustomEditor(Date.class, dateEditor);
    }
}
