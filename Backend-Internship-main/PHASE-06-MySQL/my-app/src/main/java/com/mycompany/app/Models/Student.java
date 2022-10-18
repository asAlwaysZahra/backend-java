package com.mycompany.app.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Student {
    private int studentID;
    private String name;
    private int favCourseID;
    private boolean isOnProbation;
    private double gpa;
}
