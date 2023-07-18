package ru.hogwarts.school.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class FacultyServiceTest {

    @Mock
    private FacultyRepository facultyRepository;

    @InjectMocks
    private FacultyService facultyService;

    @BeforeEach
    public void init() {
        facultyService = new FacultyService(facultyRepository);
    }

    @Test
    public void creatFaculty() {
        Faculty expected = new Faculty(4L, "Slizz", "Black");
        Mockito.when(facultyRepository.save(expected))
                .thenReturn(expected);
        Faculty actual = facultyService.createFaculty(expected);
        assertEquals(expected, actual);
    }

    @Test
    public void getFacultyPositive() {
        Faculty expected = new Faculty(4L, "Slizz", "Black");
        Mockito.when(facultyRepository.findById(1L))
                .thenReturn(Optional.of(expected));
        Faculty actual = facultyService.getFaculty(1L);
        assertEquals(expected, actual);
    }

    @Test
    public void getFacultyNegative() {
        Mockito.when(facultyRepository.findById(10L))
                .thenReturn(Optional.empty());
        Faculty actual = facultyService.getFaculty(10L);
        assertNull(actual);
    }

    @Test
    public void updateFacultyPositive() {
        Faculty expected = new Faculty(4L, "Slizz", "Black");
        Mockito.when(facultyRepository.save(expected)).thenReturn(expected);
        Faculty actual = facultyService.updateFaculty(expected);
        assertEquals(expected, actual);
    }

    @Test
    public void deleteFacultyPositive() {
        facultyService.deleteFaculty(1L);
        Mockito.verify(facultyRepository, Mockito.times(1)).deleteById(Mockito.any());
    }

    @Test
    public void getFacultyByColorPositive() {
        List<Faculty> expected = new ArrayList<>(List.of(new Faculty(4L, "Slizz", "Black")));
        Mockito.when(facultyRepository.findByColor("Black")).thenReturn(expected);
        List<Faculty> actual = facultyService.getFacultyByColor("Black");
        assertIterableEquals(expected,actual);
    }

    @Test
    public void getFacultyByColorNegative() {
        List<Faculty> expected = new ArrayList<>();
        Mockito.when(facultyRepository.findByColor("Black")).thenReturn(expected);
        List<Faculty> actual = facultyService.getFacultyByColor("Black");
        assertIterableEquals(expected,actual);
    }

}
