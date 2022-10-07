package com.example.demo.controller;

import com.example.demo.model.Professor;
import com.example.demo.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProfessorController {
    @Autowired
    private ProfessorService professorService;

    // Save operation
    @PostMapping("/professors")
    public Professor saveProfessor(@RequestBody Professor professor) {
        return professorService.saveProfessor(professor);
    }

    // Read operation
    @GetMapping("/professors")
    public List<Professor> fetchProfessorList() {
        return professorService.fetchProfessorList();
    }

    // Update operation
    @PutMapping("/professors/{id}")
    public Professor updateProfessor(@RequestBody Professor professor, @PathVariable("id") Integer professorId) {
        return professorService.updateProfessor(professor, professorId);
    }

    // Delete operation
    @DeleteMapping("/professors/{id}")
    public String deleteProfessorById(@PathVariable("id") Integer professorId) {
        professorService.deleteProfessorById(professorId);
        return "Deleted Successfully";
    }
}
