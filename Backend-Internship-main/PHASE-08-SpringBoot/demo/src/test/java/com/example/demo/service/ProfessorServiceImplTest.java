package com.example.demo.service;

import com.example.demo.model.Course;
import com.example.demo.model.Professor;
import com.example.demo.model.Score;
import com.example.demo.model.Student;
import com.example.demo.model.request.ScoreRequest;
import com.example.demo.model.response.AcceptCourseResponse;
import com.example.demo.model.response.ScoreResponse;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.ProfessorRepository;
import com.example.demo.repository.ScoreRepository;
import com.example.demo.repository.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ProfessorServiceImplTest {

    @Mock
    ProfessorRepository professorRepository;

    @Mock
    CourseRepository courseRepository;

    @Mock
    ScoreRepository scoreRepository;

    @Mock
    StudentRepository studentRepository;

    @InjectMocks
    ProfessorServiceImpl professorService;

    @Test
    public void scoreStudent() {
        Course c = new Course(1, "c1", 10);
        Professor p = new Professor(1, "p1", Set.of(c));
        Student s = new Student(1, "s1", 1, true, 11.89);
        Score score = new Score(1, s, c, 0);
        Score updatedScore = new Score(1, s, c, 12.7);

        when(professorRepository.findById(any(Integer.class))).thenReturn(Optional.of(p));
        when(studentRepository.findById(any(Integer.class))).thenReturn(Optional.of(s));
        when(scoreRepository.findByStudentAndCourse(any(Integer.class), any(Integer.class))).thenReturn(score);
        when(scoreRepository.save(any(Score.class))).thenReturn(updatedScore);

        ScoreRequest request = new ScoreRequest(1, 1, 12.7);
        ScoreResponse response = ScoreResponse.builder().studentId(1).courseId(1).score(12.7).build();

        assertEquals(response, professorService.scoreStudent(1, request));
    }

    @Test
    public void acceptCourse() {
        Course c = new Course(1, "c1", 10);
        Professor p = new Professor(1, "p1", new HashSet<>());

        when(professorRepository.findById(any(Integer.class))).thenReturn(Optional.of(p));
        when(courseRepository.findById(any(Integer.class))).thenReturn(Optional.of(c));
        when(professorRepository.save(any(Professor.class))).thenReturn(p);

        AcceptCourseResponse response = new AcceptCourseResponse(1, 1);

        assertEquals(response, professorService.acceptCourse(1, 1));
    }

    @Test
    public void saveProfessor() {
        Professor professor = Professor.builder().professorId(1).name("p1").build();

        when(professorRepository.save(any(Professor.class))).thenReturn(professor);
        Professor returnedProfessor = professorService.saveProfessor(professor.request());

        verify(professorRepository, times(1)).save(any(Professor.class));
        assertEquals(professor, returnedProfessor);
    }

    @Test
    public void fetchProfessorList() {
        when(professorRepository.findAll()).thenReturn(List.of(Professor.builder().professorId(1).name("p1").build(),
                Professor.builder().professorId(2).name("p2").build(),
                Professor.builder().professorId(3).name("p3").build()));

        assertEquals(3, professorService.fetchProfessorList().size());
    }

    @Test
    public void updateProfessor() {
        Professor professor = Professor.builder().professorId(1).name("p1").build();
        Professor updatedProfessor = Professor.builder().professorId(1).name("p2").build();

        when(professorRepository.findById(1)).thenReturn(Optional.of(professor));
        when(professorRepository.save(professor)).thenReturn(updatedProfessor);
        Professor returnedProfessor = professorService.updateProfessor(updatedProfessor.request(), 1);

        verify(professorRepository, times(1)).save(any(Professor.class));
        assertEquals(updatedProfessor, returnedProfessor);
    }

    @Test
    public void deleteProfessorById() {
        professorService.deleteProfessorById(0);
        verify(professorRepository, times(1)).deleteById(any(Integer.class));
    }
}