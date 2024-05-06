package com.example.generator;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.example.generator.SpringUtils.camelName;

public class Main {
    private static final Path ROOT_XML = Path.of("byXml/src/main/java/com/example/mybank_xml");

    public static void main(String[] args) throws IOException {
        genPassword();
    }

    private static void createDaoBeans() throws IOException {
        String template = "<bean id=\"%s\" class=\"com.example.mybank_xml.dao.%sImpl\" />";

        Path daoDir = ROOT_XML.resolve("dao");
        Files.walk(daoDir)
                .filter(p -> p.getFileName().toString().endsWith("Dao.java"))
                .map(p -> p.getFileName().toString().substring(0, p.getFileName().toString().length() - 5))
                .map(s -> String.format(template, camelName(s), s))
                .forEach(System.out::println);
    }

    private static void genPassword() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("admin"));
        System.out.println(encoder.encode("cust1"));
        System.out.println(encoder.encode("cust2"));
    }
}
