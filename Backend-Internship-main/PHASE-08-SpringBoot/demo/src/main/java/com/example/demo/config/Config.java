package com.example.demo.config;

import com.example.demo.model.Course;
import com.example.demo.service.CourseService;
import com.example.demo.service.CourseServiceImpl;
import com.example.demo.service.ProfessorService;
import com.example.demo.service.ProfessorServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public Course course() {
        return new Course();
    }

    @Bean
    public CourseService courseService() {
        return new CourseServiceImpl();
    }

    @Bean
    public ProfessorService professorService() {
        return new ProfessorServiceImpl();
    }

}
