package com.example.demo.controller;

import com.example.demo.model.Professor;
import com.example.demo.model.request.ProfessorRequest;
import com.example.demo.model.request.ScoreRequest;
import com.example.demo.model.response.AcceptCourseResponse;
import com.example.demo.model.response.ScoreResponse;
import com.example.demo.service.ProfessorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class ProfessorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfessorService service;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void saveProfessor() throws Exception {

        Professor professor = Professor.builder()
                .name("new p").build();

        when(service.saveProfessor(any(ProfessorRequest.class))).thenReturn(professor);

        String json = mapper.writeValueAsString(professor);

        mockMvc.perform(post("/professors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(json));
    }

    @Test
    public void saveScore() throws Exception {
        ScoreResponse response = ScoreResponse.builder()
                .score(10.9).courseId(1).studentId(1).build();

        when(service.scoreStudent(any(Integer.class), any(ScoreRequest.class))).thenReturn(response);

        String json = mapper.writeValueAsString(response);

        mockMvc.perform(put("/professors/1/score")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(json));
    }

    @Test
    public void fetchProfessorList() throws Exception {
        List<Professor> list = List.of(new Professor(1, "p1", null),
                new Professor(2, "p2", null),
                new Professor(3, "p3", null));

        when(service.fetchProfessorList()).thenReturn(list);

        String json = mapper.writeValueAsString(list);
        mockMvc.perform(get("/professors")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(json));
    }

    @Test
    public void updateProfessor() throws Exception {
        int id = 10;
        Professor professor = new Professor(id, "p1", null);

        when(service.updateProfessor(any(ProfessorRequest.class), any(Integer.class))).thenReturn(professor);

        String json = mapper.writeValueAsString(professor);
        String uri = String.format("/professors/%d", id);

        mockMvc.perform(put(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string(json));
    }

    @Test
    public void deleteProfessorById() throws Exception {
        mockMvc.perform(delete("/professors/2"))
                .andExpect(status().isOk());
    }

    @Test
    public void acceptCourse() throws Exception {
        AcceptCourseResponse response = new AcceptCourseResponse(1, 1);

        when(service.acceptCourse(any(Integer.class), any(Integer.class))).thenReturn(response);

        String json = mapper.writeValueAsString(response);

        mockMvc.perform(put("/professors/1/accept/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(json));
    }
}