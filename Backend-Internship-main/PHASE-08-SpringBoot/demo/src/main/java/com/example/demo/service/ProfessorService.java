package com.example.demo.service;

import com.example.demo.model.Professor;
import com.example.demo.model.request.ProfessorRequest;
import com.example.demo.model.request.ScoreRequest;
import com.example.demo.model.response.AcceptCourseResponse;
import com.example.demo.model.response.ScoreResponse;

import java.util.List;

public interface ProfessorService {

    ScoreResponse scoreStudent(int professorId, ScoreRequest score);

    AcceptCourseResponse acceptCourse(int professorId, int courseId);

    Professor saveProfessor(ProfessorRequest professor);

    List<Professor> fetchProfessorList();

    Professor updateProfessor(ProfessorRequest professor, Integer professorId);

    void deleteProfessorById(Integer professorId);
}
