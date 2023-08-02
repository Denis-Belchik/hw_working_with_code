package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {

    @LocalServerPort
    private int localPort;
    @Autowired
    private StudentController studentController;
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private StudentService studentService;
    private long id;
    private int age;
    private Student student;

    @BeforeEach
    public void init() {
        student = new Student();
        student.setName("qwe");
        age = 10;
        student.setAge(age);
        id = studentService.createStudent(student).getId();
    }

    @AfterEach
    public void end() {
        studentService.deleteStudent(id);
    }

    @Test
    void contextLoads() {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    public void getStudentTest() {
        Assertions.assertThat(this.testRestTemplate.getForObject(
                        "http://localhost:" + localPort + "/student/" + id, String.class))
                .isNotNull();
    }

    @Test
    public void getStudentsByAgeBetweenTest() {
        Assertions.assertThat(this.testRestTemplate.getForObject(
                        "http://localhost:" + localPort + "/student?min=2&max=20", String.class))
                .isNotNull();
    }

    @Test
    public void getStudentsByFacultyIdTest() {
        Assertions.assertThat(this.testRestTemplate.getForObject(
                        "http://localhost:" + localPort + "/faculty?id=1", String.class))
                .isNotNull();
    }

    @Test
    public void creatStudentTest() throws Exception {
        String object = this.testRestTemplate.postForObject(
                "http://localhost:" + localPort + "/student", student, String.class);
        JSONObject jsonObject = new JSONObject(object);
        long id = jsonObject.getLong("id");
        Assertions.assertThat(jsonObject)
                .isNotNull();
        studentService.deleteStudent(id);
    }

    @Test
    public void updateStudentTest() {
        Student expected = studentService.getStudent(id);
        student.setName("asd");
        this.testRestTemplate.put(
                "http://localhost:" + localPort + "/student", student, String.class);
        Student actual = studentService.getStudent(id);
        Assertions.assertThat(!expected.equals(actual)).isTrue();
    }

    @Test
    public void deleteStudentTest(){
        Student expected = studentService.getStudent(id);
        this.testRestTemplate.delete(
                "http://localhost:" + localPort + "/student/" + id);
        Student actual = studentService.getStudent(id);
        Assertions.assertThat(!expected.equals(actual)).isTrue();
    }

    @Test
    public void getStudentsByAgeTest(){
        Assertions.assertThat(this.testRestTemplate.getForObject(
                        "http://localhost:" + localPort + "/student/filter/" + age, String.class))
                .isNotNull();
    }
}
