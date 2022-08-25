package com.mycompany.app;

import com.mycompany.app.FileClasses.ReadFile;
import com.mycompany.app.FileClasses.WriteInFile;
import com.mycompany.app.Models.Employee;

import java.io.IOException;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        try {

            List<Employee> list = ReadFile.readJson("EmployeeData.json");

            WriteInFile.writeToXml(list, "EmployeeData.xml");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
