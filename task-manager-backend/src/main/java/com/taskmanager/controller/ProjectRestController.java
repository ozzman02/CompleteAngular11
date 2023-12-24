package com.taskmanager.controller;

import com.taskmanager.entity.Project;
import com.taskmanager.model.ProjectDTO;
import com.taskmanager.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
