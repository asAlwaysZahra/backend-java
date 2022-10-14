package com.example.demo.repository;

import com.example.demo.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {
    @Query("select count(s) from Student s where s.favCourseId = :cid")
    Integer getFavCourseCount(@Param("cid") int id);
}
