package com.example.demo.service;

import com.example.demo.model.Course;
import com.example.demo.model.Score;
import com.example.demo.model.Student;
import com.example.demo.model.request.StudentRequest;
import com.example.demo.model.response.RegisterCourseResponse;
import com.example.demo.model.response.ScoreResponse;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.ScoreRepository;
import com.example.demo.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    private CourseRepository courseRepository;
    private ScoreRepository scoreRepository;

    @Override
    public Student getStudent(int id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isEmpty()) return null;
        return student.get();
    }

    @Override
    public Student saveStudent(StudentRequest student) {

        Student created = Student.builder()
                .name(student.getName())
                .favCourseId(student.getFavCourseId())
                .gpa(student.getGpa())
                .isOnProbation(student.isOnProbation())
                .build();

        return studentRepository.save(created);
    }

    @Override
    public List<Student> fetchStudentList() {
        return (List<Student>) studentRepository.findAll();
    }

    @Override
    public Student updateStudent(StudentRequest student, Integer studentId) {

        Student stu = studentRepository.findById(studentId).orElseThrow();

        if (Objects.nonNull(student.getName()) && !student.getName().isEmpty())
            stu.setName(student.getName());

        stu.setGpa(student.getGpa());
        stu.setOnProbation(student.isOnProbation());
        stu.setFavCourseId(student.getFavCourseId());

        return studentRepository.save(stu);
    }

    @Override
    public void deleteStudentById(Integer studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    public RegisterCourseResponse register(Integer studentId, Integer courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        Optional<Student> student = studentRepository.findById(studentId);

        scoreRepository.save(Score.builder()
                .course(course.orElseThrow())
                .student(student.orElseThrow())
                .build());

        return new RegisterCourseResponse(studentId, courseId);
    }

    @Override
    public ScoreResponse getCourseScore(Integer studentId, Integer courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        Optional<Student> student = studentRepository.findById(studentId);

        Score score = scoreRepository.findByStudentAndCourse(student.orElseThrow().getStudentId(),
                course.orElseThrow().getCourseId());

        return ScoreResponse.builder()
                .studentId(score.getStudent().getStudentId())
                .courseId(score.getCourse().getCourseId())
                .score(score.getScore())
                .build();
    }

    @Override
    public void deleteCourse(int studentId, int courseId) {
        Score score = scoreRepository.findByStudentAndCourse(studentId, courseId);
        System.out.println(score);
        scoreRepository.delete(score);
    }
}
