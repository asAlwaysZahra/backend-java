package com.mycompany.app;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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

            List<Employee> list = readJson();

            writeToXml(list);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ------------------------------------------------------------------------------------
    public static List<Employee> readJson() throws IOException {

        String jsonArray = Files.readString(Path.of("EmployeeData.json"));

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(jsonArray, new TypeReference<>() {
        });
    }

    // ------------------------------------------------------------------------------------
    public static void writeToXml(List<Employee> list) throws IOException {

        XmlMapper xmlMapper = new XmlMapper();

        xmlMapper.writeValue(new File("EmployeeData.xml"), list);
    }
}
