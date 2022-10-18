package com.example.demo.service;

import com.example.demo.model.Course;
import com.example.demo.model.response.CourseAvgResponse;
import com.example.demo.model.response.CourseFavCountResponse;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.ScoreRepository;
import com.example.demo.repository.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CourseServiceImplTest {

    @Mock
    CourseRepository courseRepository;

    @Mock
    ScoreRepository scoreRepository;

    @Mock
    StudentRepository studentRepository;

    @InjectMocks
    CourseServiceImpl courseService;

    @Test
    public void saveCourse() {
        Course course = Course.builder()
                .courseId(1)
                .name("c1")
                .capacity(29)
                .build();

        when(courseRepository.save(any(Course.class))).thenReturn(course);
        Course returnedCourse = courseService.saveCourse(course.request());

        verify(courseRepository, times(1)).save(any(Course.class));
        assertEquals(course, returnedCourse);
    }

    @Test
    public void fetchCourseList() {
        when(courseRepository.findAll()).thenReturn(List.of(new Course(1, "c1", 21),
                new Course(2, "c2", 22),
                new Course(3, "c3", 30)));

        assertEquals(3, courseService.fetchCourseList().size());
    }

    @Test
    public void updateCourse() {
        Course course = Course.builder()
                .courseId(1)
                .name("c1")
                .capacity(29)
                .build();

        Course updatedCourse = Course.builder()
                .courseId(1)
                .name("c1")
                .capacity(30)
                .build();

        when(courseRepository.findById(1)).thenReturn(Optional.of(course));
        when(courseRepository.save(course)).thenReturn(updatedCourse);
        Course returnedCourse = courseService.updateCourse(updatedCourse.request(), 1);

        verify(courseRepository, times(1)).save(any(Course.class));
        assertEquals(updatedCourse, returnedCourse);
    }

    @Test
    public void deleteCourseById() {
        courseService.deleteCourseById(0);
        verify(courseRepository, times(1)).deleteById(any(Integer.class));
    }

    @Test
    public void getAvgScore() {
        when(scoreRepository.getScoreAverage(1)).thenReturn(19.0);
        assertEquals(new CourseAvgResponse(1, 19.0), courseService.getAvgScore(1));
    }

    @Test
    public void getFavCount() {
        when(studentRepository.getFavCourseCount(1)).thenReturn(19);
        assertEquals(new CourseFavCountResponse(1, 19), courseService.getFavCount(1));
    }
}