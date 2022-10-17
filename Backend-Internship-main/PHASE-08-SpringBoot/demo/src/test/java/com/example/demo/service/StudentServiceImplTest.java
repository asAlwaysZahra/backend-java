package com.example.demo.service;

import com.example.demo.model.Course;
import com.example.demo.model.Score;
import com.example.demo.model.Student;
import com.example.demo.model.response.RegisterCourseResponse;
import com.example.demo.model.response.ScoreResponse;
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
public class StudentServiceImplTest {

    @Mock
    CourseRepository courseRepository;

    @Mock
    ScoreRepository scoreRepository;

    @Mock
    StudentRepository studentRepository;

    @InjectMocks
    StudentServiceImpl studentService;

    @Test
    public void saveStudent() {
        Student student = Student.builder()
                .studentId(1)
                .name("s1")
                .isOnProbation(false)
                .gpa(12.4)
                .favCourseId(1)
                .build();

        when(studentRepository.save(any(Student.class))).thenReturn(student);
        Student returnedStudent = studentService.saveStudent(student.request());

        verify(studentRepository, times(1)).save(any(Student.class));
        assertEquals(student, returnedStudent);
    }

    @Test
    public void fetchStudentList() {
        when(studentRepository.findAll()).thenReturn(List.of(new Student(1, "s1", 1, false, 12.56),
                new Student(2, "s2", 0, true, 11.56),
                new Student(3, "s3", 4, false, 19.56)));

        assertEquals(3, studentService.fetchStudentList().size());
    }

    @Test
    public void updateStudent() {
        Student student = Student.builder()
                .studentId(1)
                .name("c1")
                .favCourseId(1)
                .isOnProbation(false)
                .gpa(12.7)
                .build();

        Student updatedStudent = Student.builder()
                .studentId(1)
                .name("c2")
                .favCourseId(2)
                .isOnProbation(true)
                .gpa(19.7)
                .build();

        when(studentRepository.findById(student.getStudentId())).thenReturn(Optional.of(student));
        when(studentRepository.save(student)).thenReturn(updatedStudent);
        Student returnedStudent = studentService.updateStudent(updatedStudent.request(), student.getStudentId());

        verify(studentRepository, times(1)).save(any(Student.class));
        assertEquals(updatedStudent, returnedStudent);
    }

    @Test
    public void deleteStudentById() {
        studentService.deleteStudentById(0);
        verify(studentRepository, times(1)).deleteById(any(Integer.class));
    }

    @Test
    public void register() {
        Course c = new Course(1, "c1", 100);
        Student s = new Student(1, "s1", 100, true, 10.91);
        Score registerRecord = new Score();

        when(courseRepository.findById(1)).thenReturn(Optional.of(c));
        when(studentRepository.findById(1)).thenReturn(Optional.of(s));
        when(scoreRepository.save(any(Score.class))).thenReturn(registerRecord);

        RegisterCourseResponse response = new RegisterCourseResponse(s.getStudentId(), c.getCourseId());
        assertEquals(response, studentService.register(s.getStudentId(), c.getCourseId()));
    }

    @Test
    public void getCourseScore() {
        Course c = new Course(1, "c1", 100);
        Student s = new Student(1, "s1", 100, true, 10.91);
        Score score = new Score(1, s, c, 10.5);

        when(courseRepository.findById(1)).thenReturn(Optional.of(c));
        when(studentRepository.findById(1)).thenReturn(Optional.of(s));
        when(scoreRepository.findByStudentAndCourse(any(Integer.class), any(Integer.class))).thenReturn(score);

        ScoreResponse response = ScoreResponse.builder()
                .studentId(1).courseId(1).score(10.5)
                .build();
        assertEquals(response, studentService.getCourseScore(s.getStudentId(), c.getCourseId()));
    }

    @Test
    public void deleteCourse() {
        Course c = new Course(1, "c1", 100);
        Student s = new Student(1, "s1", 100, true, 10.91);
        Score score = new Score(1, s, c, 10.5);

        when(scoreRepository.findByStudentAndCourse(any(Integer.class), any(Integer.class))).thenReturn(score);
        studentService.deleteCourse(s.getStudentId(), c.getCourseId());
        verify(scoreRepository, times(1)).delete(score);
    }
}