package com.mycompany.app.FileClasses;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.mycompany.app.Models.Employee;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ReadFile {

    private static ReadFile instance;

    public static ReadFile getInstance() {

        if (instance != null)
            return instance;

        synchronized (ReadFile.class) {
            if (instance == null)
                instance = new ReadFile();
        }

        return instance;
    }

    public List<Employee> readJson(String path) throws IOException {

        String jsonArray = Files.readString(Path.of(path));

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Employee.class, new EmployeeDeserializer());
        mapper.registerModule(module);

        return mapper.readValue(jsonArray, new TypeReference<>() {
        });
    }
}
