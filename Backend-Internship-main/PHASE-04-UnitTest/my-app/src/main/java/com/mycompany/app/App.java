package com.mycompany.app;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        try {

            List<Employee> list = readJson("EmployeeData.json");

            writeToXml(list, "EmployeeData.xml");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ------------------------------------------------------------------------------------
    public static List<Employee> readJson(String path) throws IOException {

        String jsonArray = Files.readString(Path.of(path));

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Employee.class, new EmployeeDeserializer());
        mapper.registerModule(module);

        return mapper.readValue(jsonArray, new TypeReference<>() {
        });
    }

    // ------------------------------------------------------------------------------------
    public static void writeToXml(List<Employee> list, String path) throws IOException {

        XmlMapper xmlMapper = new XmlMapper();

        xmlMapper.writeValue(new File(path), list);
    }
}
