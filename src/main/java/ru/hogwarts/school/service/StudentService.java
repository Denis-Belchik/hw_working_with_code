package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;
import java.util.Map;

@Service
public class StudentService {

    private final Map<Long, Student> studentMap = new HashMap<>();

    private long id = 0;

    public Student createStudent(Student student) {
        student.setId(++id);
        return studentMap.put(id, student);
    }

    public Student getStudent(Long id) {
        if (studentMap.containsKey(id)) {
            return studentMap.get(id);
        }
        return null;
    }

    public Student updateStudent(Student student) {
        if (studentMap.containsKey(student.getId())) {
            studentMap.put(student.getId(), student);
            return student;
        }
        return null;
    }

    public Student deleteStudent(long id) {
        if (studentMap.containsKey(id)) {
            return studentMap.remove(id);
        }
        return null;
    }

}
