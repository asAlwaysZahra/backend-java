package com.example.demo.model.request;

import lombok.Value;

@Value
public class StudentRequest {
    String name;
    int favCourseId;
    boolean isOnProbation;
    double gpa;
}
