package com.mycompany.app.FileClasses;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.mycompany.app.Models.Employee;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ReadFile {
    public static List<Employee> readJson(String path) throws IOException {

        String jsonArray = Files.readString(Path.of(path));

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Employee.class, new EmployeeDeserializer());
        mapper.registerModule(module);

        return mapper.readValue(jsonArray, new TypeReference<>() {
        });
    }
}
