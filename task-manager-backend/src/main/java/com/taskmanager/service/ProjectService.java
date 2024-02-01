package com.taskmanager.service;

import com.taskmanager.model.ProjectDTO;

import java.util.List;
import java.util.UUID;

public interface ProjectService {

    List<ProjectDTO> findAll();

    ProjectDTO saveOrUpdate(ProjectDTO projectDTO);

    String deleteProjectById(UUID id);

    List<ProjectDTO> search(String searchBy, String searchText);

}
