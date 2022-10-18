package com.mycompany.app.Models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Course {
    private int courseID;
    private String name;
    private int capacity;
}
