package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.model.request.StudentRequest;
import com.example.demo.model.response.RegisterCourseResponse;
import com.example.demo.model.response.ScoreResponse;
import com.example.demo.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class StudentController {

    private StudentService studentService;

    // Save operation
    @PostMapping("/students")
    public ResponseEntity<Student> saveStudent(@RequestBody StudentRequest student) {
        return new ResponseEntity<>(studentService.saveStudent(student), HttpStatus.OK);
    }

    // Read operation
    @GetMapping("/students")
    public ResponseEntity<List<Student>> fetchStudentList() {
        return new ResponseEntity<>(studentService.fetchStudentList(), HttpStatus.OK);
    }

    // Update operation
    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody StudentRequest student,
                                                 @PathVariable("id") Integer studentId) {
        return new ResponseEntity<>(studentService.updateStudent(student, studentId), HttpStatus.OK);
    }

    // Delete operation
    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable("id") Integer studentId) {
        studentService.deleteStudentById(studentId);
        return ResponseEntity.ok().build();
    }

    // Register for a course
    @PostMapping("/students/{studentId}/register/{courseId}")
    public ResponseEntity<RegisterCourseResponse> registerCourse(@PathVariable("studentId") Integer studentId,
                                                                 @PathVariable("courseId") Integer courseId) {
        return new ResponseEntity<>(studentService.register(studentId, courseId), HttpStatus.OK);
    }

    // Delete course
    @PutMapping("/students/{studentId}/delete/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable("studentId") Integer studentId,
                                             @PathVariable("courseId") Integer courseId) {
        studentService.deleteCourse(studentId, courseId);
        return ResponseEntity.ok().build();
    }

    // Get course score
    @GetMapping("/students/{studentId}/scores/{courseId}")
    public ResponseEntity<ScoreResponse> getScore(@PathVariable("studentId") Integer studentId,
                                                  @PathVariable("courseId") Integer courseId) {
        return new ResponseEntity<>(studentService.getCourseScore(studentId, courseId), HttpStatus.OK);
    }
}
