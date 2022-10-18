package com.example.demo.model;

import com.example.demo.model.request.CourseRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "course_id")
    private int courseId;
    private String name;
    private int capacity;

    public CourseRequest request() {
        return new CourseRequest(name, capacity);
    }
}
