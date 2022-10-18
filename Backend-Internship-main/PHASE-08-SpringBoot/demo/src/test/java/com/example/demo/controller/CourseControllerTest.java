package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.model.request.CourseRequest;
import com.example.demo.model.response.CourseAvgResponse;
import com.example.demo.model.response.CourseFavCountResponse;
import com.example.demo.service.CourseService;
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
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService service;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void saveCourse() throws Exception {

        Course course = Course.builder()
                .courseId(1)
                .name("c1")
                .capacity(301)
                .build();

        when(service.saveCourse(any(CourseRequest.class))).thenReturn(course);

        String json = mapper.writeValueAsString(course);

        mockMvc.perform(post("/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(json));
    }

    @Test
    public void getAvgScore() throws Exception {
        CourseAvgResponse response = new CourseAvgResponse(1, 18.0);
        when(service.getAvgScore(any(Integer.class))).thenReturn(response);

        mockMvc.perform(get("/courses/avg/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void favCount() throws Exception {
        CourseFavCountResponse response = new CourseFavCountResponse(1, 200);
        when(service.getFavCount(any(Integer.class))).thenReturn(response);

        String json = mapper.writeValueAsString(response);
        mockMvc.perform(get("/courses/fav/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(json));
    }

    @Test
    public void fetchCourseList() throws Exception {
        List<Course> list = List.of(new Course(1, "c1", 100),
                new Course(2, "c2", 200),
                new Course(3, "c3", 300));

        when(service.fetchCourseList()).thenReturn(list);

        String json = mapper.writeValueAsString(list);
        mockMvc.perform(get("/courses")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(json));
    }

    @Test
    public void updateCourse() throws Exception {
        int id = 10;
        Course course = new Course(id, "c1", 100);

        when(service.updateCourse(any(CourseRequest.class), any(Integer.class))).thenReturn(course);

        String json = mapper.writeValueAsString(course);
        String uri = String.format("/courses/%d", id);

        mockMvc.perform(put(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string(json));
    }

    @Test
    public void deleteCourseById() throws Exception {
        mockMvc.perform(delete("/courses/2"))
                .andExpect(status().isOk());
    }
}