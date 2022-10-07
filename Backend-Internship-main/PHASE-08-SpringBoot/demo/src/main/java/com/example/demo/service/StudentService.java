package com.example.demo.service;

import com.example.demo.model.Student;

import java.util.List;

public interface StudentService {

    Student getStudent(int id);
    Student saveStudent(Student student);
    List<Student> fetchStudentList();
    Student updateStudent(Student student, Integer studentId);
    void deleteStudentById(Integer studentId);
}
