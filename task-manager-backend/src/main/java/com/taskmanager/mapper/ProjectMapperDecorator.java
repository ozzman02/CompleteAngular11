package com.taskmanager.mapper;

import com.taskmanager.entity.Project;
import com.taskmanager.model.ProjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class ProjectMapperDecorator implements ProjectMapper {

    private ProjectMapper projectMapper;

    @Autowired
    @Qualifier("delegate")
    public void setProjectMapper(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }

    @Override
    public ProjectDTO projectToProjectDto(Project project) {
        ProjectDTO projectDTO = projectMapper.projectToProjectDto(project);
        projectDTO.setClientLocationId(project.getClientLocation().getId());
        return projectDTO;
    }
}
