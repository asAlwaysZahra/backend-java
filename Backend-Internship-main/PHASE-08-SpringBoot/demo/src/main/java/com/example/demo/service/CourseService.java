package com.example.demo.service;

import com.example.demo.model.Course;
import com.example.demo.model.request.CourseRequest;
import com.example.demo.model.response.CourseAvgResponse;
import com.example.demo.model.response.CourseFavCountResponse;

import java.util.List;

public interface CourseService {

    Course saveCourse(CourseRequest course);

    List<Course> fetchCourseList();

    Course updateCourse(CourseRequest course, Integer courseId);

    void deleteCourseById(Integer courseId);

    CourseAvgResponse getAvgScore(Integer courseId);

    CourseFavCountResponse getFavCount(Integer courseId);
}
