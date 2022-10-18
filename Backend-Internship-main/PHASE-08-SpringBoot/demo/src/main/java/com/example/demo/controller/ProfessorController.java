package com.example.demo.controller;

import com.example.demo.model.Professor;
import com.example.demo.model.request.ProfessorRequest;
import com.example.demo.model.request.ScoreRequest;
import com.example.demo.model.response.AcceptCourseResponse;
import com.example.demo.model.response.ScoreResponse;
import com.example.demo.service.ProfessorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProfessorController {

    private final ProfessorService professorService;

    // Save operation
    @PostMapping("/professors")
    public ResponseEntity<Professor> saveProfessor(@RequestBody ProfessorRequest professor) {
        return new ResponseEntity<>(professorService.saveProfessor(professor), HttpStatus.OK);
    }

    // Score students
    @PutMapping("/professors/{id}/score")
    public ResponseEntity<ScoreResponse> saveScore(@PathVariable("id") Integer professorId,
                                                   @RequestBody ScoreRequest score) {
        return new ResponseEntity<>(professorService.scoreStudent(professorId, score), HttpStatus.OK);
    }

    // Read operation
    @GetMapping("/professors")
    public ResponseEntity<List<Professor>> fetchProfessorList() {
        return new ResponseEntity<>(professorService.fetchProfessorList(), HttpStatus.OK);
    }

    // Update operation
    @PutMapping("/professors/{id}")
    public ResponseEntity<Professor> updateProfessor(@RequestBody ProfessorRequest professor,
                                                     @PathVariable("id") Integer professorId) {
        return new ResponseEntity<>(professorService.updateProfessor(professor, professorId), HttpStatus.OK);
    }

    // Delete operation
    @DeleteMapping("/professors/{id}")
    public ResponseEntity<Void> deleteProfessorById(@PathVariable("id") Integer professorId) {
        professorService.deleteProfessorById(professorId);
        return ResponseEntity.ok().build();
    }

    // Accept course by professor
    @PutMapping("/professors/{pid}/accept/{cid}")
    public ResponseEntity<AcceptCourseResponse> acceptCourse(@PathVariable("pid") Integer pid,
                                                             @PathVariable("cid") Integer cid) {
        return new ResponseEntity<>(professorService.acceptCourse(pid, cid), HttpStatus.OK);
    }
}
