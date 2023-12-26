package com.taskmanager.controller;

import com.taskmanager.model.ProjectDTO;
import com.taskmanager.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ProjectRestController {

    private final ProjectService projectService;

    @GetMapping("/projects")
    public List<ProjectDTO> getProjects() {
        return projectService.findAll();
    }

    @PostMapping("/projects")
    public ProjectDTO saveProject(@Valid @RequestBody ProjectDTO projectDTO) {
        return projectService.saveProject(projectDTO);
    }

    @PutMapping("/projects")
    public ProjectDTO updateProject(@Valid @RequestBody ProjectDTO projectDTO) {
        return projectService.updateProject(projectDTO);
    }

}
