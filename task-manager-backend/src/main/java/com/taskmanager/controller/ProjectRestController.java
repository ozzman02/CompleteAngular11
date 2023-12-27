package com.taskmanager.controller;

import com.taskmanager.model.ProjectDTO;
import com.taskmanager.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = {"http://localhost:4200"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/projects")
public class ProjectRestController {

    private final ProjectService projectService;

    @GetMapping
    public List<ProjectDTO> getProjects() {
        return projectService.findAll();
    }

    @PostMapping
    public ProjectDTO saveProject(@Valid @RequestBody ProjectDTO projectDTO) {
        return projectService.saveProject(projectDTO);
    }

    @PutMapping
    public ProjectDTO updateProject(@Valid @RequestBody ProjectDTO projectDTO) {
        return projectService.updateProject(projectDTO);
    }

    @DeleteMapping
    public String deleteProjectById(@RequestParam String projectId) {
        return projectService.deleteProjectById(UUID.fromString(projectId.trim()));
    }

    @GetMapping("/search/{searchBy}/{searchText}")
    public List<ProjectDTO> search(@PathVariable String searchBy, @PathVariable String searchText) {
        return projectService.search(searchBy, searchText);
    }

}
