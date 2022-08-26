package com.mycompany.app.Controllers;

import com.mycompany.app.FileClasses.ReadFile;
import com.mycompany.app.FileClasses.WriteInFile;
import com.mycompany.app.Models.Employee;

import java.io.IOException;
import java.util.List;

public class AppController {

    public void readAndWrite() {
        try {

            ReadFile reader = ReadFile.getInstance();
            List<Employee> list = reader.readJson("EmployeeData.json");

            WriteInFile writer = WriteInFile.getInstance();
            writer.writeToXml(list, "EmployeeData.xml");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
