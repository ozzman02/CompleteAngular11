package com.taskmanager.controller;

import com.taskmanager.model.ProjectDTO;
import com.taskmanager.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = {"http://localhost:4200"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/projects")
public class ProjectRestController {

    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<?> getProjects() {
        return new ResponseEntity<>(projectService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveProject(@Valid @RequestBody ProjectDTO projectDTO) {
        return new ResponseEntity<>(projectService.saveProject(projectDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> updateProject(@Valid @RequestBody ProjectDTO projectDTO) {
        return new ResponseEntity<>(projectService.updateProject(projectDTO), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteProjectById(@RequestParam String projectId) {
        return new ResponseEntity<>(projectService.deleteProjectById(UUID.fromString(projectId.trim())),
                HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search/{searchBy}/{searchText}")
    public ResponseEntity<?> search(@PathVariable String searchBy, @PathVariable String searchText) {
        return new ResponseEntity<>(projectService.search(searchBy, searchText), HttpStatus.OK) ;
    }

}
