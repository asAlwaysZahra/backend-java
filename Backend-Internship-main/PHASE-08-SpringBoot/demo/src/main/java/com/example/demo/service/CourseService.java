package com.example.demo.service;

import com.example.demo.model.Course;

import java.util.List;

public interface CourseService {

    Course getCourse(int id);
    Course saveCourse(Course course);
    List<Course> fetchCourseList();
    Course updateCourse(Course course, Integer courseId);
    void deleteCourseById(Integer courseId);
}
