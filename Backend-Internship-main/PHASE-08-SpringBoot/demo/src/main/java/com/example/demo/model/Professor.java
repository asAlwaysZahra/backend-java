package com.example.demo.model;

import com.example.demo.model.request.ProfessorRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "professor_id")
    private int professorId;
    private String name;

    @ManyToMany
    @JoinTable(
            name = "professor_course",
            joinColumns = @JoinColumn(name = "professor_Id"),
            inverseJoinColumns = @JoinColumn(name = "course_Id")
    )
    private Set<Course> courses;

    public ProfessorRequest request() {
        return new ProfessorRequest(name);
    }
}
