package com.example.demo.service;

import com.example.demo.model.Professor;

import java.util.List;

public interface ProfessorService {

    Professor getProfessor(int id);
    Professor saveProfessor(Professor professor);
    List<Professor> fetchProfessorList();
    Professor updateProfessor(Professor professor, Integer professorId);
    void deleteProfessorById(Integer professorId);
}
