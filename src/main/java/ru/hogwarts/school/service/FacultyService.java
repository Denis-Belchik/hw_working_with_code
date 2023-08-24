package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.List;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    private final Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        logger.info("createFaculty");
        facultyRepository.save(faculty);
        return faculty;
    }

    public Faculty getFaculty(Long id) {
        logger.info("getFaculty");
        return facultyRepository.findById(id).orElse(null);
    }

    public Faculty updateFaculty(Faculty faculty) {
        logger.info("updateFaculty");
        facultyRepository.save(faculty);
        return faculty;
    }

    public void deleteFaculty(long id) {
        logger.info("deleteFaculty");
        facultyRepository.deleteById(id);
    }

    public List<Faculty> getFacultyByColor(String color) {
        logger.info("getFacultyByColor");
        return facultyRepository.findByColorIgnoreCase(color);
    }

    public List<Faculty> getFacultyByName(String name){
        logger.info("getFacultyByName");
        return facultyRepository.findByNameIgnoreCase(name);
    }

    public List<Faculty> getAllFaculty(){
        logger.info("getAllFaculty");
        return facultyRepository.findAll();
    }

    public Faculty getByStudentsId(long id){
        logger.info("getByStudentsId");
        return facultyRepository.findByStudentsId(id);
    }

}
