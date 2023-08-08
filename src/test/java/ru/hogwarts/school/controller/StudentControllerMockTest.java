package ru.hogwarts.school.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class StudentControllerMockTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StudentRepository studentRepository;
    @MockBean
    private AvatarService avatarService;
    @MockBean
    private FacultyService facultyService;
    @SpyBean
    private StudentService studentService;
    @InjectMocks
    private StudentController studentController;
    private final long id = 1;
    private final int age = 10;
    private final String name = "qwe";
    private final Student student = new Student(id, name, age);

    @Test
    public void getStudentTest() throws Exception {
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/" + 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.age").value(age))
                .andExpect(jsonPath("$.name").value(name));
    }

    @Test
    public void getStudentsByAgeBetweenTest() throws Exception {
        when(studentRepository.findByAgeBetween(any(Integer.class), any(Integer.class)))
                .thenReturn(List.of(student));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student?min=10&max=20")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].age").value(age))
                .andExpect(jsonPath("$.[0].name").value(name));
    }

    @Test
    public void getStudentsByFacultyId() throws Exception {
        when(studentRepository.findByFacultyId(any(Long.class)))
                .thenReturn(List.of(student));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/faculty?id=1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].age").value(age))
                .andExpect(jsonPath("$.[0].name").value(name));
    }

    @Test
    public void creatStudentTEst() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("age", age);
        when(studentRepository.save(any(Student.class)))
                .thenReturn(student);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student")
                        .content(jsonObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.age").value(age))
                .andExpect(jsonPath("$.name").value(name));
    }

    @Test
    public void updateStudent() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("age", age);
        when(studentRepository.save(any(Student.class)))
                .thenReturn(student);
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/student")
                        .content(jsonObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.age").value(age))
                .andExpect(jsonPath("$.name").value(name));
    }

    @Test
    public void deleteStudent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/student/1"))
                .andExpect(status().isOk());
        Mockito.verify(studentRepository, Mockito.times(1))
                .deleteById(any(Long.class));
    }

    @Test
    public void getStudentsByAgeTest() throws Exception {
        when(studentRepository.findByAge(any(Integer.class)))
                .thenReturn(List.of(student));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/filter/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].age").value(age))
                .andExpect(jsonPath("$.[0].name").value(name));
    }

}
