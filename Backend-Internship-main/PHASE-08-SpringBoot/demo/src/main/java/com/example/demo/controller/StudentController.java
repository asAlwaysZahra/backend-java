package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    // Save operation
    @PostMapping("/students")
    public Student saveStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    // Read operation
    @GetMapping("/students")
    public List<Student> fetchStudentList() {
        return studentService.fetchStudentList();
    }

    // Update operation
    @PutMapping("/students/{id}")
    public Student updateStudent(@RequestBody Student student, @PathVariable("id") Integer studentId) {
        return studentService.updateStudent(student, studentId);
    }

    // Delete operation
    @DeleteMapping("/students/{id}")
    public String deleteStudentById(@PathVariable("id") Integer studentId) {
        studentService.deleteStudentById(studentId);
        return "Deleted Successfully";
    }
}
