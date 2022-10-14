package com.example.demo.model.request;

import lombok.Value;

@Value
public class ScoreRequest {
    int studentId;
    int courseId;
    double score;
}
