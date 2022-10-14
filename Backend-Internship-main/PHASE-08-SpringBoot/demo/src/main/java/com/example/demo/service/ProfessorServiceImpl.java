package com.example.demo.service;

import com.example.demo.model.Course;
import com.example.demo.model.Professor;
import com.example.demo.model.Score;
import com.example.demo.model.Student;
import com.example.demo.model.request.ProfessorRequest;
import com.example.demo.model.request.ScoreRequest;
import com.example.demo.model.response.AcceptCourseResponse;
import com.example.demo.model.response.ScoreResponse;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.ProfessorRepository;
import com.example.demo.repository.ScoreRepository;
import com.example.demo.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class ProfessorServiceImpl implements ProfessorService {

    private ProfessorRepository professorRepository;
    private ScoreRepository scoreRepository;
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;

    @Override
    public ScoreResponse scoreStudent(int professorId, ScoreRequest request) {

        Optional<Professor> professor = professorRepository.findById(professorId);
        Optional<Student> student = studentRepository.findById(request.getStudentId());
        Set<Course> courseSet;

        courseSet = professor.orElseThrow().getCourses();

        Optional<Course> courseFound = courseSet.stream().filter(course -> course.getCourseId() == request.getCourseId()).findFirst();

        Score scoreFound = scoreRepository.findByStudentAndCourse(student.orElseThrow().getStudentId(),
                courseFound.orElseThrow().getCourseId());
        scoreFound.setScore(request.getScore());

        Score score = scoreRepository.save(scoreFound);

        return ScoreResponse.builder()
                .studentId(score.getStudent().getStudentId())
                .courseId(score.getCourse().getCourseId())
                .score(score.getScore())
                .build();
    }

    @Override
    public AcceptCourseResponse acceptCourse(int professorId, int courseId) {
        Optional<Professor> professor = professorRepository.findById(professorId);
        Optional<Course> course = courseRepository.findById(courseId);

        professor.orElseThrow().getCourses().add(course.orElseThrow());
        professorRepository.save(professor.get());
        return new AcceptCourseResponse(professorId, courseId);
    }

    @Override
    public Professor saveProfessor(ProfessorRequest professor) {
        Professor created = new Professor();
        created.setName(professor.getName());
        return professorRepository.save(created);
    }

    @Override
    public List<Professor> fetchProfessorList() {
        return (List<Professor>) professorRepository.findAll();
    }

    @Override
    public Professor updateProfessor(ProfessorRequest professor, Integer professorId) {

        Professor prof = professorRepository.findById(professorId).orElseThrow();

        if (Objects.nonNull(professor.getName()) && !"".equalsIgnoreCase(professor.getName()))
            prof.setName(professor.getName());

        return professorRepository.save(prof);
    }

    @Override
    public void deleteProfessorById(Integer professorId) {
        professorRepository.deleteById(professorId);
    }
}
