package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.model.request.StudentRequest;
import com.example.demo.model.response.RegisterCourseResponse;
import com.example.demo.model.response.ScoreResponse;
import com.example.demo.service.StudentService;
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
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService service;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void saveStudent() throws Exception {

        Student student = Student.builder()
                .studentId(1)
                .name("s1")
                .favCourseId(31)
                .gpa(11.4)
                .isOnProbation(true)
                .build();

        when(service.saveStudent(any(StudentRequest.class))).thenReturn(student);

        String json = mapper.writeValueAsString(student);

        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(json));
    }

    @Test
    public void fetchStudentList() throws Exception {
        List<Student> list = List.of(new Student(1, "s1", 100, false, 19),
                new Student(2, "s2", 200, false, 12.9),
                new Student(3, "s3", 300, true, 9));

        when(service.fetchStudentList()).thenReturn(list);

        String json = mapper.writeValueAsString(list);
        mockMvc.perform(get("/students")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(json));
    }

    @Test
    public void updateStudent() throws Exception {
        int id = 10;
        Student student = new Student(id, "s1", 100, false, 12.98);

        when(service.updateStudent(any(StudentRequest.class), any(Integer.class))).thenReturn(student);

        String json = mapper.writeValueAsString(student);
        String uri = String.format("/students/%d", id);

        mockMvc.perform(put(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string(json));
    }

    @Test
    public void deleteStudentById() throws Exception {
        mockMvc.perform(delete("/students/20"))
                .andExpect(status().isOk());
    }

    @Test
    public void registerCourse() throws Exception {

        int cid = 1, sid = 10;
        RegisterCourseResponse response = new RegisterCourseResponse(sid, cid);

        when(service.register(any(Integer.class), any(Integer.class))).thenReturn(response);

        String json = mapper.writeValueAsString(response);

        String uri = String.format("/students/%d/register/%d", sid, cid);
        mockMvc.perform(post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(json));
    }

    @Test
    public void deleteCourse() throws Exception {
        int cid = 1, sid = 1;
        String uri = String.format("/students/%d/delete/%d", sid, cid);
        mockMvc.perform(delete(uri))
                .andExpect(status().isOk());
    }

    @Test
    public void getScore() throws Exception {
        int cid = 1, sid = 10;
        ScoreResponse response = ScoreResponse.builder()
                .score(10.9)
                .studentId(sid)
                .courseId(cid)
                .build();

        when(service.getCourseScore(any(Integer.class), any(Integer.class))).thenReturn(response);

        String json = mapper.writeValueAsString(response);

        String uri = String.format("/students/%d/scores/%d", sid, cid);
        mockMvc.perform(get(uri)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(json));
    }
}