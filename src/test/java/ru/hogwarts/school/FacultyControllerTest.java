package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerTest {
    @LocalServerPort
    private int localPort;
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private FacultyController facultyController;
    @Autowired
    private FacultyService facultyService;

    private long id;
    private String name = "qwe";
    private String color = "zxc";
    private Faculty faculty;

    @BeforeEach
    public void init() {
        faculty = new Faculty();
        faculty.setName(name);
        faculty.setColor(color);
        id = facultyService.createFaculty(faculty).getId();
    }

    @AfterEach
    public void end() {
        facultyService.deleteFaculty(id);
    }

    @Test
    void contextLoads() {
        Assertions.assertThat(facultyController).isNotNull();
    }

    @Test
    public void getFacultyTest(){
        Assertions.assertThat(this.testRestTemplate.getForObject(
                        "http://localhost:" + localPort + "/faculty/" + id, String.class))
                .isNotNull();
    }

    @Test
    public void getFacultyByColorOrNameTest(){
        Assertions.assertThat(this.testRestTemplate.getForObject(
                        "http://localhost:" + localPort + "/faculty?color" + color, String.class))
                .isNotNull();
    }

    @Test
    public void getFacultyByStudentsIdTest(){
        Assertions.assertThat(this.testRestTemplate.getForObject(
                        "http://localhost:" + localPort + "/student?id=1", String.class))
                .isNotNull();
    }

    @Test
    public void creatFacultyTest() throws JSONException {
        String object = this.testRestTemplate.postForObject(
                "http://localhost:" + localPort + "/faculty", faculty, String.class);
        JSONObject jsonObject = new JSONObject(object);
        long id = jsonObject.getLong("id");
        Assertions.assertThat(jsonObject).isNotNull();
        facultyService.deleteFaculty(id);
    }

    @Test
    public void updateFacultyTest(){
        Faculty expected = facultyService.getFaculty(id);
        faculty.setName("qaz");
        this.testRestTemplate.put(
                "http://localhost:" + localPort + "/faculty", faculty, String.class);
        Faculty actual = facultyService.getFaculty(id);
        Assertions.assertThat(!expected.equals(actual)).isTrue();
    }

    @Test
    public void deleteFacultyTest(){
        Faculty expected = facultyService.getFaculty(id);
        this.testRestTemplate.delete(
                "http://localhost:" + localPort + "/faculty/" + id);
        Faculty actual = facultyService.getFaculty(id);
        Assertions.assertThat(!expected.equals(actual)).isTrue();
    }

    @Test
    public void getFacultyByColorTest(){
        Assertions.assertThat(this.testRestTemplate.getForObject(
                        "http://localhost:" + localPort + "/faculty/filter/" + color, String.class))
                .isNotNull();
    }

}
