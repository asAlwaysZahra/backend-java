package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student getStudent(int id) {
        Optional<Student> student= studentRepository.findById(id);
        if (student.isEmpty()) return null;
        return student.get();
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> fetchStudentList() {
        return (List<Student>) studentRepository.findAll();
    }

    @Override
    public Student updateStudent(Student student, Integer studentId) {
        Student depDB = studentRepository.findById(studentId).get();

        if (Objects.nonNull(student.getName()) && !"".equalsIgnoreCase(student.getName()))
            depDB.setName(student.getName());

        depDB.setStudentID(student.getStudentID());
        depDB.setGpa(student.getGpa());
        depDB.setOnProbation(student.isOnProbation());
        depDB.setFavCourseID(student.getFavCourseID());

        return studentRepository.save(depDB);
    }

    @Override
    public void deleteStudentById(Integer studentId) {
        studentRepository.deleteById(studentId);
    }
}
