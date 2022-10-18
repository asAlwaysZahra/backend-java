package com.mycompany.app;

import com.mycompany.app.Controllers.DatabaseController;
import com.mycompany.app.Models.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        DatabaseController database = DatabaseController.getInstance();
        System.out.println("Hello World!");

//        sample data

//        database.addCourse(new Course(1, "c1", 60));
//        database.addCourse(new Course(2, "c2", 50));
//        database.addCourse(new Course(3, "c3", 20));
//        database.addCourse(new Course(4, "c4", 30));
//        database.addCourse(new Course(5, "c5", 20));
//        database.addCourse(new Course(6, "c6", 40));
//
//        database.addProfessor(new Professor(1, "p1"));
//        database.addProfessor(new Professor(2, "p2"));
//        database.addProfessor(new Professor(3, "p3"));
//        database.addProfessor(new Professor(4, "p4"));
//        database.addProfessor(new Professor(5, "p5"));
//
//        Student s1 = Student.builder()
//                .studentID(1)
//                .name("s1")
//                .favCourseID(2)
//                .isOnProbation(false)
//                .gpa(12.5)
//                .build();
//
//        Student s2 = Student.builder()
//                .studentID(2)
//                .name("s2")
//                .favCourseID(3)
//                .isOnProbation(false)
//                .gpa(18.75)
//                .build();
//
//        Student s3 = Student.builder()
//                .studentID(3)
//                .name("s3")
//                .favCourseID(5)
//                .isOnProbation(true)
//                .gpa(10)
//                .build();
//
//        database.addStudent(s1);
//        database.addStudent(s2);
//        database.addStudent(s3);

        database.changeFavCourse(1, 5);
        System.out.println(database.favCourseCount(5));
        System.out.println(database.studentsGPA(10.5));
        database.deleteCourse(1, 4);
        System.out.println(database.averageCourseScore(4));
    }
}
