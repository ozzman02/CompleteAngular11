package com.taskmanager.mapper;

import com.taskmanager.entity.Project;
import com.taskmanager.model.ProjectDTO;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = ClientLocationMapper.class)
public interface ProjectMapper {

    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    Project projectDtoToProject(ProjectDTO projectDTO);

    @Mapping(target = "clientLocationId", expression = "java(project.getClientLocation() != null ? project.getClientLocation().getId() : null)")
    ProjectDTO projectToProjectDto(Project project);

}
