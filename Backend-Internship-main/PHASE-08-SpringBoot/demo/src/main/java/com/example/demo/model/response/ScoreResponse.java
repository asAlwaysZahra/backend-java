package com.example.demo.model.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ScoreResponse {
    int studentId;
    int courseId;
    double score;
}
