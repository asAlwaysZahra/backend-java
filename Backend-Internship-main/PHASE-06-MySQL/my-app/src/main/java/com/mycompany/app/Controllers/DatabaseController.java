package com.mycompany.app.Controllers;

import com.mycompany.app.Models.Course;
import com.mycompany.app.Models.Professor;
import com.mycompany.app.Models.Student;
import lombok.Cleanup;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// singleton class
public class DatabaseController {
    private static DatabaseController instance;
    private final String user = "root";
    private final String password = "";

    private DatabaseController() throws SQLException, ClassNotFoundException {
        @Cleanup
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/phase6", user, password);
        Class.forName("com.mysql.cj.jdbc.Driver");

        // create database
        String sql = "CREATE DATABASE IF NOT EXISTS `phase6`";
        Statement s = connection.prepareStatement(sql);
        s.execute(sql);

        // create tables
        createStudentsTable();
        createProfessorsTable();
        createCoursesTable();

        createProfessor_CoursesTable();
        createStudent_CoursesTable();
    }

    @SneakyThrows
    public static DatabaseController getInstance() {
        if (instance == null) instance = new DatabaseController();
        return instance;
    }

    // CREATE TABLES ===================================================================================================

    @SneakyThrows
    public void createStudentsTable() {
        @Cleanup
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/phase6", user, password);
        Class.forName("com.mysql.cj.jdbc.Driver");
        String sql = "CREATE TABLE IF NOT EXISTS `students` (" +
                "studentID INT NOT NULL," +
                "name VARCHAR(20)," +
                "favCourse INT," +
                "isOnProbation TINYINT," +
                "gpa DOUBLE," +
                "PRIMARY KEY (studentID)" +
                ");";

        Statement s = connection.prepareStatement(sql);
        s.execute(sql);
    }

    @SneakyThrows
    public void createProfessorsTable() {
        @Cleanup
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/phase6", user, password);
        Class.forName("com.mysql.cj.jdbc.Driver");
        String sql = "CREATE TABLE IF NOT EXISTS `professors` (" +
                "professorID INT NOT NULL," +
                "name VARCHAR(20)," +
                "PRIMARY KEY (professorID)" +
                ");";

        Statement s = connection.prepareStatement(sql);
        s.execute(sql);
    }

    @SneakyThrows
    public void createCoursesTable() {
        @Cleanup
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/phase6", user, password);
        Class.forName("com.mysql.cj.jdbc.Driver");
        String sql = "CREATE TABLE IF NOT EXISTS `courses` (" +
                "courseID INT NOT NULL," +
                "name VARCHAR(20)," +
                "capacity INT," +
                "PRIMARY KEY (courseID)" +
                ");";

        Statement s = connection.prepareStatement(sql);
        s.execute(sql);
    }

    // CREATE LINKING TABLES ===========================================================================================

    @SneakyThrows
    public void createProfessor_CoursesTable() {
        @Cleanup
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/phase6", user, password);
        Class.forName("com.mysql.cj.jdbc.Driver");

        String sql = "CREATE TABLE IF NOT EXISTS `professor-course` (" +
                "professorID INT," +
                "courseID INT," +
                "FOREIGN KEY (courseID) REFERENCES courses.courseID," +
                "FOREIGN KEY (professorID) REFERENCES professors.professorID," +
                "CONSTRAINT prkey PRIMARY KEY (professorID, courseID)" +
                ");";

        Statement s = connection.prepareStatement(sql);
        s.execute(sql);
    }

    @SneakyThrows
    public void createStudent_CoursesTable() {
        @Cleanup
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/phase6", user, password);
        Class.forName("com.mysql.cj.jdbc.Driver");

        String sql = "CREATE TABLE IF NOT EXISTS `student-course` (" +
                "studentID INT," +
                "courseID INT," +
                "score DOUBLE," +
                "FOREIGN KEY (courseID) REFERENCES courses.courseID," +
                "FOREIGN KEY (studentID) REFERENCES students.studentID," +
                "CONSTRAINT prkey PRIMARY KEY (studentID, courseID)" +
                ");";

        Statement s = connection.prepareStatement(sql);
        s.execute(sql);
    }

    // INSERT QUERIES ==================================================================================================

    @SneakyThrows
    public void addStudent(Student s) {
        @Cleanup
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/phase6", user, password);
        Class.forName("com.mysql.cj.jdbc.Driver");
        String sql;

        if (s.isOnProbation())
            sql = String.format("INSERT INTO `students` VALUES (%d, '%s', %d, %d, %f)",
                    s.getStudentID(), s.getName(), s.getFavCourseID(), 1, s.getGpa());
        else
            sql = String.format("INSERT INTO `students` VALUES (%d, '%s', %d, %d, %f)",
                    s.getStudentID(), s.getName(), s.getFavCourseID(), 0, s.getGpa());

        Statement statement = connection.prepareStatement(sql);
        statement.execute(sql);
    }

    @SneakyThrows
    public void addCourse(Course c) {
        @Cleanup
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/phase6", user, password);
        Class.forName("com.mysql.cj.jdbc.Driver");
        String sql = String.format("INSERT INTO `courses` VALUES (%d, '%s', %d)",
                c.getCourseID(), c.getName(), c.getCapacity());
        Statement statement = connection.prepareStatement(sql);
        statement.execute(sql);
    }

    @SneakyThrows
    public void addProfessor(Professor p) {
        @Cleanup
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/phase6", user, password);
        Class.forName("com.mysql.cj.jdbc.Driver");
        String sql = String.format("INSERT INTO `professors` VALUES (%d, '%s')", p.getProfessorID(), p.getName());
        Statement statement = connection.prepareStatement(sql);
        statement.execute(sql);
    }

    // FOREIGN KEY =====================================================================================================

    @SneakyThrows
    public void acceptCourseByProfessor(int professorID, int courseID) {
        @Cleanup
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/phase6", user, password);
        Class.forName("com.mysql.cj.jdbc.Driver");
        String sql = String.format("INSERT INTO `professor-course` VALUES (%d, %d);", professorID, courseID);
        Statement s = connection.prepareStatement(sql);
        s.execute(sql);
    }

    @SneakyThrows
    public void register(int studentID, int courseID) {
        @Cleanup
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/phase6", user, password);
        Class.forName("com.mysql.cj.jdbc.Driver");
        String sql = String.format("INSERT INTO `student-course` (studentID, courseID) VALUES (%d, %d);", studentID, courseID);
        Statement s = connection.prepareStatement(sql);
        s.execute(sql);
    }

    // DELETE ==========================================================================================================

    @SneakyThrows
    public void deleteCourse(int studentID, int courseID) {
        @Cleanup
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/phase6", user, password);
        Class.forName("com.mysql.cj.jdbc.Driver");
        String sql = String.format("DELETE FROM `student-course` WHERE studentID=%d AND courseID=%d;", studentID, courseID);
        Statement s = connection.prepareStatement(sql);
        s.execute(sql);
    }

    // UPDATE ==========================================================================================================

    @SneakyThrows
    public void changeFavCourse(int studentID, int courseID) {
        @Cleanup
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/phase6", user, password);
        Class.forName("com.mysql.cj.jdbc.Driver");
        String sql = String.format("UPDATE `students` SET favCourse=%d WHERE studentID=%d", courseID, studentID);
        Statement s = connection.prepareStatement(sql);
        s.execute(sql);
    }

    @SneakyThrows
    public void score(int professorID, int studentID, double score) {
        @Cleanup
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/phase6", user, password);
        Class.forName("com.mysql.cj.jdbc.Driver");
        String sql = String.format("UPDATE `student-course` " +
                        "INNER JOIN `professor-course` " +
                        "ON `professor-course`.courseID = `student-course`.courseID " +
                        "SET score = %f " +
                        "WHERE `professor-course`.professorID = %d " +
                        "AND `student-course`.studentID = %d "
                , score, professorID, studentID);
        Statement s = connection.prepareStatement(sql);
        s.execute(sql);
    }

    // SELECT, AVG, COUNT ==============================================================================================

    @SneakyThrows
    public double averageCourseScore(int courseID) {
        @Cleanup
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/phase6", user, password);
        Class.forName("com.mysql.cj.jdbc.Driver");

        String sql = String.format("SELECT AVG(score) AS avg FROM `student-course` WHERE courseID=%d;", courseID);
        Statement s = connection.prepareStatement(sql);
        ResultSet rs = s.executeQuery(sql);

        rs.next();
        return rs.getDouble("avg");
    }

    @SneakyThrows
    public List<Student> studentsGPA(double scoreAtLeast) {
        @Cleanup
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/phase6", user, password);
        Class.forName("com.mysql.cj.jdbc.Driver");

        String sql = String.format("SELECT * FROM `students` WHERE gpa>=%f;", scoreAtLeast);
        Statement s = connection.prepareStatement(sql);
        ResultSet rs = s.executeQuery(sql);

        ArrayList<Student> students = new ArrayList<>();

        while (rs.next()) {
            Student st = Student.builder()
                    .studentID(rs.getInt("studentID"))
                    .name(rs.getString("name"))
                    .favCourseID(rs.getInt("favCourse"))
                    .isOnProbation(rs.getBoolean("isOnProbation"))
                    .gpa(rs.getDouble("gpa"))
                    .build();

            students.add(st);
        }

        return students;
    }

    @SneakyThrows
    public int favCourseCount(int courseID) {
        @Cleanup
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/phase6", user, password);
        Class.forName("com.mysql.cj.jdbc.Driver");

        String sql = String.format("SELECT COUNT(*) AS count " +
                "FROM `students` " +
                "WHERE favCourse=%d " +
                "GROUP BY favCourse;", courseID);

        Statement s = connection.prepareStatement(sql);
        ResultSet rs = s.executeQuery(sql);

        rs.next();
        return rs.getInt("count");
    }
}