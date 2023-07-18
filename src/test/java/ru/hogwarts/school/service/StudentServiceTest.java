package ru.hogwarts.school.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest {

    private final StudentService studentService = new StudentService();

    @BeforeEach
    public void init(){
        studentService.createStudent(new Student(0L, "Dima", 15));
        studentService.createStudent(new Student(0L, "Dima1", 15));
        studentService.createStudent(new Student(0L, "Dima2", 10));
    }

    @Test
    public void creatStudent() {
        Student actual = studentService.createStudent(new Student(0L, "Dima3", 15));
        Student expected = new Student(4L, "Dima3", 15);
        assertEquals(expected, actual);
    }

    @Test
    public void getStudentPositive() {
        Student actual = studentService.getStudent(1L);
        Student expected = new Student(1L, "Dima", 15);
        assertEquals(expected, actual);
    }

    @Test
    public void getStudentNegative() {
        Student actual = studentService.getStudent(10L);
        assertNull(actual);
    }

    @Test
    public void updateStudentPositive() {
        Student actual = studentService.updateStudent(new Student(2L, "Dima1", 15));
        Student expected = new Student(2L, "Dima1", 15);
        assertEquals(expected, actual);
    }

    @Test
    public void updateStudentNegative() {
        Student actual = studentService.updateStudent(new Student(10L, "Dima", 15));
        assertNull(actual);
    }

    @Test
    public void deleteStudentPositive() {
        Student actual = studentService.deleteStudent(2L);
        Student expected = new Student(2L, "Dima1", 15);
        assertEquals(expected, actual);
    }

    @Test
    public void deleteStudentNegative() {
        Student actual = studentService.deleteStudent(10L);
        assertNull(actual);
    }

    @Test
    public void getStudentsByAgePositive() {
        List<Student> actual = studentService.getStudentsByAge(10);
        List<Student> expected = new ArrayList<>(List.of(new Student(3L, "Dima2", 10)));
        assertIterableEquals(expected,actual);
    }

    @Test
    public void getStudentsByAgeNegative() {
        List<Student> actual = studentService.getStudentsByAge(20);
        List<Student> expected = new ArrayList<>();
        assertIterableEquals(expected,actual);
    }

}
