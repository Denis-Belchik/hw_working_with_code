package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable long id) {
        Faculty faculty = facultyService.getFaculty(id);
        if (faculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping
    public ResponseEntity<List<Faculty>> getFacultyByColorOrName(@RequestParam(required = false) String name,
                                                                 @RequestParam(required = false) String color) {
        if (name != null && !name.isBlank())
            return ResponseEntity.ok(facultyService.getFacultyByName(name));
        if (color != null && !color.isBlank())
            return ResponseEntity.ok(facultyService.getFacultyByColor(color));
        return ResponseEntity.ok(facultyService.getAllFaculty());
    }

    @GetMapping("/student")
    public ResponseEntity<Faculty> getFacultyByStudentsId(@RequestParam long id) {
        Faculty faculty = facultyService.getByStudentsId(id);
        if (faculty == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.ok(faculty);
    }

    @PostMapping
    public Faculty creatFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty) {
        Faculty upFaculty = facultyService.updateFaculty(faculty);
        if (upFaculty == null) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(upFaculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/filter/{color}")
    public List<Faculty> getFacultyByColor(@PathVariable String color) {
        return facultyService.getFacultyByColor(color);
    }

    @GetMapping("/filter/long-name")
    public ResponseEntity<Optional<String>> findLongName() {
        return ResponseEntity.ok(facultyService.findLongName());
    }

}
