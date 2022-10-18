package com.example.demo.service;

import com.example.demo.model.Course;
import com.example.demo.model.request.CourseRequest;
import com.example.demo.model.response.CourseAvgResponse;
import com.example.demo.model.response.CourseFavCountResponse;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.ScoreRepository;
import com.example.demo.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;
    private ScoreRepository scoreRepository;
    private StudentRepository studentRepository;

    @Override
    public Course saveCourse(CourseRequest course) {
        Course created = Course.builder()
                .name(course.getName())
                .capacity(course.getCapacity())
                .build();

        return courseRepository.save(created);
    }

    @Override
    public List<Course> fetchCourseList() {
        return (List<Course>) courseRepository.findAll();
    }

    @Override
    public Course updateCourse(CourseRequest course, Integer courseId) {
        Course crs = courseRepository.findById(courseId).orElseThrow();

        if (Objects.nonNull(course.getName()) && !course.getName().isEmpty())
            crs.setName(course.getName());

        crs.setCapacity(course.getCapacity());

        return courseRepository.save(crs);
    }

    @Override
    public void deleteCourseById(Integer courseId) {
        courseRepository.deleteById(courseId);
    }

    @Override
    public CourseAvgResponse getAvgScore(Integer courseId) {
        return new CourseAvgResponse(courseId, scoreRepository.getScoreAverage(courseId));
    }

    @Override
    public CourseFavCountResponse getFavCount(Integer courseId) {
        return new CourseFavCountResponse(courseId, studentRepository.getFavCourseCount(courseId));
    }
}
