package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class Professor {
    @Id
    private int professorID;
    private String name;

    @ManyToMany
    @JoinTable(
            name = "professor_course",
            joinColumns = @JoinColumn(name = "professorID"),
            inverseJoinColumns = @JoinColumn(name = "courseID")
    )
    Set<Course> courses;
}
