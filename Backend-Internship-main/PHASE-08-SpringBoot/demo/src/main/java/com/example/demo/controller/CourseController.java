package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.service.CourseService;
import com.example.demo.service.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;

    // Save operation
    @PostMapping("/courses")
    public Course saveCourse(@RequestBody Course course) {
        return courseService.saveCourse(course);
    }

    // Read operation
    @GetMapping("/courses")
    public List<Course> fetchCourseList() {
        return courseService.fetchCourseList();
    }

    // Update operation
    @PutMapping("/courses/{id}")
    public Course updateCourse(@RequestBody Course course, @PathVariable("id") Integer courseId) {
        return courseService.updateCourse(course, courseId);
    }

    // Delete operation
    @DeleteMapping("/courses/{id}")
    public String deleteCourseById(@PathVariable("id") Integer courseId) {
        courseService.deleteCourseById(courseId);
        return "Deleted Successfully";
    }
}
