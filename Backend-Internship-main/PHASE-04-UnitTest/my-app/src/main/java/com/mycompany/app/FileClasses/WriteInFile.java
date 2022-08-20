package com.mycompany.app.FileClasses;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.mycompany.app.Models.Employee;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class WriteInFile {
    public static void writeToXml(List<Employee> list, String path) throws IOException {

        XmlMapper xmlMapper = new XmlMapper();

        xmlMapper.writeValue(new File(path), list);
    }
}
