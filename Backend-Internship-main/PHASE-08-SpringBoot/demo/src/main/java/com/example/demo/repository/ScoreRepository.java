package com.example.demo.repository;

import com.example.demo.model.Score;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends CrudRepository<Score, Integer> {

    @Query("select s from Score s where s.student.studentId = :sid and s.course.courseId = :cid")
    Score findByStudentAndCourse(@Param("sid") int studentId, @Param("cid") int courseId);

    @Query("select avg(s.score) from Score s where s.course.courseId = :cid")
    double getScoreAverage(@Param("cid") int courseId);
}
