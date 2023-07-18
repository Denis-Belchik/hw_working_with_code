package ru.hogwarts.school.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Faculty;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class FacultyServiceTest {

    private final FacultyService facultyService = new FacultyService();

    @BeforeEach
    public void init(){
        facultyService.createFaculty(new Faculty(0L, "Griff", "red"));
        facultyService.createFaculty(new Faculty(0L, "Slizz", "green"));
        facultyService.createFaculty(new Faculty(0L, "Griff", "red"));
    }

    @Test
    public void creatFaculty() {
        Faculty actual = facultyService.createFaculty(new Faculty(0L, "Griff", "black"));
        Faculty expected = new Faculty(4L, "Griff", "black");
        assertEquals(expected, actual);
    }

    @Test
    public void getFacultyPositive() {
        Faculty actual = facultyService.getFaculty(1L);
        Faculty expected = new Faculty(1L, "Griff", "red");
        assertEquals(expected, actual);
    }

    @Test
    public void getFacultyNegative() {
        Faculty actual = facultyService.getFaculty(10L);
        assertNull(actual);
    }

    @Test
    public void updateFacultyPositive() {
        Faculty actual = facultyService.updateFaculty(new Faculty(2L, "Slizz", "green"));
        Faculty expected = new Faculty(2L, "Slizz", "green");
        assertEquals(expected, actual);
    }

    @Test
    public void updateFacultyNegative() {
        Faculty actual = facultyService.updateFaculty(new Faculty(10L, "Slizzz", "green"));
        assertNull(actual);
    }

    @Test
    public void deleteFacultyPositive() {
        Faculty actual = facultyService.deleteFaculty(2L);
        Faculty expected = new Faculty(2L, "Slizz", "green");
        assertEquals(expected, actual);
    }

    @Test
    public void deleteFacultyNegative() {
        Faculty actual = facultyService.deleteFaculty(10L);
        assertNull(actual);
    }

    @Test
    public void getFacultyByColorPositive() {
        List<Faculty> actual = facultyService.getFacultyByColor("green");
        List<Faculty> expected = new ArrayList<>(List.of(new Faculty(2L, "Slizz", "green")));
        assertIterableEquals(expected,actual);
    }

    @Test
    public void getFacultyByColorNegative() {
        List<Faculty> actual = facultyService.getFacultyByColor("greens");
        List<Faculty> expected = new ArrayList<>();
        assertIterableEquals(expected,actual);
    }

}
