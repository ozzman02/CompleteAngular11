package com.taskmanager.mapper;

import com.taskmanager.entity.Project;
import com.taskmanager.model.ProjectDTO;
import org.mapstruct.Mapper;

@Mapper
public interface ProjectMapper {

    Project projectDtoToProject(ProjectDTO projectDTO);

    ProjectDTO projectToProjectDto(Project project);

}
