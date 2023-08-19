package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private final Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        logger.info("createStudent");
        return studentRepository.save(student);
    }

    public Student getStudent(Long id) {
        logger.info("getStudent");
        return studentRepository.findById(id).orElse(null);
    }

    public Student updateStudent(Student student) {
        logger.info("updateStudent");
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        logger.info("deleteStudent");
        studentRepository.deleteById(id);
    }

    public List<Student> getStudentsByAge(int age) {
        logger.info("getStudentsByAge");
        return studentRepository.findByAge(age);
    }

    public List<Student> getStudentsByAgeBetween(int min, int max) {
        logger.info("getStudentsByAgeBetween");
        return studentRepository.findByAgeBetween(min, max);
    }

    public List<Student> getByFacultyId(long id) {
        logger.info("getByFacultyId");
        return studentRepository.findByFacultyId(id);
    }

    public Integer getCountStudent() {
        logger.info("getCountStudent");
        return studentRepository.findCountStudent();
    }

    public Float getAvgAgeStudent() {
        logger.info("getAvgAgeStudent");
        return studentRepository.findAvgAgeStudent();
    }

    public List<Student> findLastFiveStudent(){
        logger.info("findLastFiveStudent");
        return studentRepository.findLastFiveStudent();
    }
}
