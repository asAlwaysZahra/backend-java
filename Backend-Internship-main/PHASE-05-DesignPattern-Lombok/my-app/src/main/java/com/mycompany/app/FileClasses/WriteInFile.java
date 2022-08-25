package com.mycompany.app.FileClasses;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.mycompany.app.Models.Employee;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class WriteInFile {

    private static WriteInFile instance;

    private WriteInFile() {
    }

    public static WriteInFile getInstance() {

        if (instance != null)
            return instance;

        synchronized (WriteInFile.class) {
            if (instance == null)
                instance = new WriteInFile();
        }

        return instance;
    }

    public void writeToXml(List<Employee> list, String path) throws IOException {

        XmlMapper xmlMapper = new XmlMapper();

        xmlMapper.writeValue(new File(path), list);
    }
}
