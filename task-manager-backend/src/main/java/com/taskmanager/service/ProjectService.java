package com.taskmanager.service;

import com.taskmanager.model.ProjectDTO;

import java.util.List;

public interface ProjectService {

    List<ProjectDTO> findAll();

    ProjectDTO saveProject(ProjectDTO projectDTO);

    ProjectDTO updateProject(ProjectDTO projectDTO);

}
