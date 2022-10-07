package com.example.demo.service;

import com.example.demo.model.Professor;
import com.example.demo.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Override
    public Professor getProfessor(int id) {
        Optional<Professor> professor= professorRepository.findById(id);
        if (professor.isEmpty()) return null;
        return professor.get();
    }

    @Override
    public Professor saveProfessor(Professor professor) {
        return professorRepository.save(professor);
    }

    @Override
    public List<Professor> fetchProfessorList() {
        return (List<Professor>) professorRepository.findAll();
    }

    @Override
    public Professor updateProfessor(Professor professor, Integer professorId) {

        Professor depDB = professorRepository.findById(professorId).get();

        if (Objects.nonNull(professor.getName()) && !"".equalsIgnoreCase(professor.getName()))
            depDB.setName(professor.getName());

        depDB.setProfessorID(professor.getProfessorID());

        return professorRepository.save(depDB);
    }

    @Override
    public void deleteProfessorById(Integer professorId) {
        professorRepository.deleteById(professorId);
    }
}
