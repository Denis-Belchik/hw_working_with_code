package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudent(@PathVariable long id) {
        Student student = studentService.getStudent(id);
        if (student == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping()
    public ResponseEntity<List<Student>> getStudentsByAgeBetween(@RequestParam int min,
                                                                 @RequestParam int max) {
        List<Student> studentList = studentService.getStudentsByAgeBetween(min, max);
        if (studentList.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.ok(studentList);
    }

    @GetMapping("/faculty")
    public ResponseEntity<List<Student>> getStudentsByFacultyId(@RequestParam long id) {
        List<Student> studentList = studentService.getByFacultyId(id);
        if (studentList.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.ok(studentList);
    }

    @PostMapping
    public Student creatStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student upStudent = studentService.updateStudent(student);
        if (upStudent == null) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(upStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/filter/{age}")
    public List<Student> getStudentsByAge(@PathVariable int age) {
        return studentService.getStudentsByAge(age);
    }

    @GetMapping("/filter/count")
    public Integer getCountStudent(){
        return studentService.getCountStudent();
    }

    @GetMapping("/filter/avg-age")
    public Float getAvgAgeStudent(){
        return studentService.getAvgAgeStudent();
    }

    @GetMapping("/filter/last-student")
    public List<Student> findLastFiveStudent(){
        return studentService.findLastFiveStudent();
    }

    @GetMapping("/filter/a-student")
    public ResponseEntity<List<String>> findFirstAStudent(){
        return ResponseEntity.ok(studentService.findFirstA());
    }

    @GetMapping("/filter/average-age-student")
    public ResponseEntity<OptionalDouble> findAverageAgeStudent(){
        return ResponseEntity.ok(studentService.findAverageAgeStudent());
    }

    @GetMapping("/sum-int")
    public ResponseEntity<Integer> findSumInt(){
        return ResponseEntity.ok(studentService.findSumInt());
    }

}
