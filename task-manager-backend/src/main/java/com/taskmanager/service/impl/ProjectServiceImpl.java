package com.taskmanager.service.impl;

import com.taskmanager.mapper.ProjectMapper;
import com.taskmanager.model.ProjectDTO;
import com.taskmanager.repository.ProjectRepository;
import com.taskmanager.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    private final ProjectMapper projectMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ProjectDTO> findAll() {
        return projectRepository.findAll().stream()
                .map(projectMapper::projectToProjectDto)
                .sorted(Comparator.comparing(ProjectDTO::getCreatedDate))
                .toList();
    }

    @Override
    @Transactional
    public ProjectDTO saveProject(ProjectDTO projectDTO) {
        projectDTO.setCreatedDate(LocalDateTime.now());
        projectDTO.setUpdateDate(LocalDateTime.now());
        return projectMapper.projectToProjectDto(projectRepository.save(projectMapper.projectDtoToProject(projectDTO)));
    }

    @Override
    public ProjectDTO updateProject(ProjectDTO projectDTO) {
        AtomicReference<ProjectDTO> projectDTOAtomicReference = new AtomicReference<>();
        projectRepository.findById(projectDTO.getId()).ifPresentOrElse(existingProject -> {
            existingProject.setProjectName(projectDTO.getProjectName());
            existingProject.setDateOfStart(projectDTO.getDateOfStart());
            existingProject.setTeamSize(projectDTO.getTeamSize());
            existingProject.setUpdateDate(LocalDateTime.now());
            existingProject.setVersion(projectDTO.getVersion() + 1);
            projectDTOAtomicReference.set(projectMapper.projectToProjectDto(existingProject));
            projectRepository.save(existingProject);
        }, () -> projectDTOAtomicReference.set(null));
        return projectDTOAtomicReference.get();
    }

}
