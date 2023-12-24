package com.taskmanager.service.impl;

import com.taskmanager.mapper.ProjectMapper;
import com.taskmanager.model.ProjectDTO;
import com.taskmanager.repository.ProjectRepository;
import com.taskmanager.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    private final ProjectMapper projectMapper;

    @Override
    public List<ProjectDTO> findAll() {
        return projectRepository.findAll().stream().map(projectMapper::projectToProjectDto).toList();
    }

}
