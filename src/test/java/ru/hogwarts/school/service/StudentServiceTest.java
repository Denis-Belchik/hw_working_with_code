package ru.hogwarts.school.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    public void init() {
        studentService = new StudentService(studentRepository);
    }

    @Test
    public void creatStudent() {
        Student expected = new Student(4L, "Dima3", 15);
        Mockito.when(studentRepository.save(expected))
                .thenReturn(expected);
        Student actual = studentService.createStudent(expected);
        assertEquals(expected, actual);
    }

    @Test
    public void getStudentPositive() {
        Student expected = new Student(1L, "Dima", 15);
        Mockito.when(studentRepository.findById(1L))
                .thenReturn(Optional.of(expected));
        Student actual = studentService.getStudent(1L);
        assertEquals(expected, actual);
    }

    @Test
    public void getStudentNegative() {
        Mockito.when(studentRepository.findById(10L))
                .thenReturn(Optional.empty());
        Student actual = studentService.getStudent(10L);
        assertNull(actual);
    }

    @Test
    public void updateStudentPositive() {
        Student expected = new Student(2L, "Dima1", 15);
        Mockito.when(studentRepository.save(expected)).thenReturn(expected);
        Student actual = studentService.updateStudent(expected);
        assertEquals(expected, actual);
    }

    @Test
    public void deleteStudentPositive() {
        studentService.deleteStudent(1L);
        Mockito.verify(studentRepository, Mockito.times(1)).deleteById(Mockito.any());
    }

    @Test
    public void getStudentsByAgePositive() {
        List<Student> expected = new ArrayList<>(List.of(new Student(3L, "Dima2", 10)));
        Mockito.when(studentRepository.findByAge(10)).thenReturn(expected);
        List<Student> actual = studentService.getStudentsByAge(10);
        assertIterableEquals(expected,actual);
    }

    @Test
    public void getStudentsByAgeNegative() {
        List<Student> expected = new ArrayList<>();
        Mockito.when(studentRepository.findByAge(20)).thenReturn(expected);
        List<Student> actual = studentService.getStudentsByAge(20);
        assertIterableEquals(expected,actual);
    }

}
