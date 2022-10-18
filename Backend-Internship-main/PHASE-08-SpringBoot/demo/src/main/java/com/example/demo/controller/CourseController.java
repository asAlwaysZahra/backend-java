package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.model.request.CourseRequest;
import com.example.demo.model.response.CourseAvgResponse;
import com.example.demo.model.response.CourseFavCountResponse;
import com.example.demo.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CourseController {

    private CourseService courseService;

    // Save operation
    @PostMapping("/courses")
    public ResponseEntity<Course> saveCourse(@RequestBody CourseRequest request) {
        return new ResponseEntity<>(courseService.saveCourse(request), HttpStatus.OK);
    }

    // Read operation
    @GetMapping("/courses")
    public ResponseEntity<List<Course>> fetchCourseList() {
        return new ResponseEntity<>(courseService.fetchCourseList(), HttpStatus.OK);
    }

    // Update operation
    @PutMapping("/courses/{id}")
    public ResponseEntity<Course> updateCourse(@RequestBody CourseRequest course,
                                               @PathVariable("id") Integer courseId) {
        return new ResponseEntity<>(courseService.updateCourse(course, courseId), HttpStatus.OK);
    }

    // Delete operation
    @DeleteMapping("/courses/{id}")
    public ResponseEntity<Void> deleteCourseById(@PathVariable("id") Integer courseId) {
        courseService.deleteCourseById(courseId);
        return ResponseEntity.ok().build();
    }

    // Get average score
    @GetMapping("/courses/avg/{id}")
    public ResponseEntity<CourseAvgResponse> getAvgScore(@PathVariable("id") Integer courseId) {
        CourseAvgResponse response = courseService.getAvgScore(courseId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Number of students with favorite course: courseId
    @GetMapping("/courses/fav/{id}")
    public ResponseEntity<CourseFavCountResponse> favCount(@PathVariable("id") Integer courseId) {
        return new ResponseEntity<>(courseService.getFavCount(courseId), HttpStatus.OK);
    }
}
