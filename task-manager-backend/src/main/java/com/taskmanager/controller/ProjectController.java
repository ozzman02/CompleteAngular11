package com.taskmanager.controller;

import com.taskmanager.model.ProjectDTO;
import com.taskmanager.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<?> getProjects() {
        return new ResponseEntity<>(projectService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    //@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<?> saveProject(@Valid @RequestBody ProjectDTO projectDTO) {
        return new ResponseEntity<>(projectService.saveOrUpdate(projectDTO), HttpStatus.CREATED);
    }

    @PutMapping
    //@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<?> updateProject(@Valid @RequestBody ProjectDTO projectDTO) {
        return new ResponseEntity<>(projectService.saveOrUpdate(projectDTO), HttpStatus.OK);
    }

    @DeleteMapping
    //@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<?> deleteProjectById(@RequestParam String projectId) {
        return new ResponseEntity<>(projectService.deleteProjectById(UUID.fromString(projectId.trim())),
                HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search/{searchBy}/{searchText}")
    //@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<?> search(@PathVariable String searchBy, @PathVariable String searchText) {
        return new ResponseEntity<>(projectService.search(searchBy, searchText), HttpStatus.OK) ;
    }

}
