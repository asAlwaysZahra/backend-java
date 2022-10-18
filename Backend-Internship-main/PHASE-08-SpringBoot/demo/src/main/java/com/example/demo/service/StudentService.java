package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.model.request.StudentRequest;
import com.example.demo.model.response.RegisterCourseResponse;
import com.example.demo.model.response.ScoreResponse;

import java.util.List;

public interface StudentService {

    Student saveStudent(StudentRequest student);

    List<Student> fetchStudentList();

    Student updateStudent(StudentRequest student, Integer studentId);

    void deleteStudentById(Integer studentId);

    RegisterCourseResponse register(Integer studentId, Integer courseId);

    ScoreResponse getCourseScore(Integer studentId, Integer courseId);

    void deleteCourse(int studentId, int courseId);
}
