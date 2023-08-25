package ru.hogwarts.school.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public List<Student> findLastFiveStudent() {
        logger.info("findLastFiveStudent");
        return studentRepository.findLastFiveStudent();
    }

    public List<String> findFirstA() {
        logger.info("findFirstA");
        return studentRepository.findAll().stream()
                .filter(s -> s.getName().startsWith("А"))
                .map(s -> s.getName().toUpperCase())
                .sorted()
                .collect(Collectors.toList());
    }

    public OptionalDouble findAverageAgeStudent() {
        logger.info("findAverageAgeStudent");
        return studentRepository.findAll().stream()
                .map(Student::getAge)
                .mapToInt(s -> s)
                .average();
    }

    public Integer findSumInt() {
        int sum =0;

//        long startStream = System.currentTimeMillis();
//        sum = Stream.iterate(1, a -> a + 1)
//                .limit(1_000_000)
//                .reduce(0, Integer::sum);
//        long finishStream = System.currentTimeMillis();
//        logger.info("Выполнение стрима {}", finishStream - startStream);
//        logger.info("Сумма {}", sum);
//
//        sum =0;
//        long startStreamParallel = System.currentTimeMillis();
//        sum = Stream.iterate(1, a -> a + 1)
//                .parallel()
//                .limit(1_000_000)
//                .reduce(0, Integer::sum);
//        long finishStreamParallel = System.currentTimeMillis();
//        logger.info("Выполнение параллельного стрима {}", finishStreamParallel - startStreamParallel);
//        logger.info("Сумма {}", sum);

        sum =0;
        long startFor = System.currentTimeMillis();
        for (int i = 0; i < 1_000_000; i++) {
            sum+=i;
        }
        long finishFor = System.currentTimeMillis();
        logger.info("Выполнение цикла {}", finishFor - startFor);
        logger.info("Сумма {}", sum);

        return sum;

    }
}
