package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByAge(int age);

    List<Student> findByAgeBetween(int min, int max);

    List<Student> findByFacultyId(long id);

    @Query(value = "SELECT count(*) FROM student ", nativeQuery = true)
    Integer findCountStudent();

    @Query(value = "SELECT avg(age) FROM student ", nativeQuery = true)
    Float findAvgAgeStudent();

    @Query(value = "SELECT * FROM student ORDER BY id DESC LIMIT 5", nativeQuery = true)
    List<Student> findLastFiveStudent();

}
