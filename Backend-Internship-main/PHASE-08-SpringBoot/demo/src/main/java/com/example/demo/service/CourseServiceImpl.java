package com.example.demo.service;

import com.example.demo.model.Course;
import com.example.demo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course getCourse(int id) {
        Optional<Course> course= courseRepository.findById(id);
        if (course.isEmpty()) return null;
        return course.get();
    }

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public List<Course> fetchCourseList() {
        return (List<Course>) courseRepository.findAll();
    }

    @Override
    public Course updateCourse(Course course, Integer courseId) {
        Course depDB = courseRepository.findById(courseId).get();

        if (Objects.nonNull(course.getName()) && !"".equalsIgnoreCase(course.getName()))
            depDB.setName(course.getName());

        depDB.setCapacity(course.getCapacity());

        return courseRepository.save(depDB);
    }

    @Override
    public void deleteCourseById(Integer courseId) {
        courseRepository.deleteById(courseId);
    }
}
