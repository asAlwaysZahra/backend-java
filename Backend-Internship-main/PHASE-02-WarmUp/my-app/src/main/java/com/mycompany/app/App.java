package com.mycompany.app;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {

        String jsonArray = Files.readString(Path.of("EmployeeData.json"));

        ObjectMapper objectMapper = new ObjectMapper();

        List<Employee> list = objectMapper.readValue(jsonArray, new TypeReference<>() {});

        // ------------------------------------------------------------------------------------

        XmlMapper xmlMapper = new XmlMapper();

        xmlMapper.writeValue(new File("EmployeeData.xml"), list);

    }
}
