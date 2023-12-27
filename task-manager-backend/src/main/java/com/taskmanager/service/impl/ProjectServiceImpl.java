package com.taskmanager.service.impl;

import com.taskmanager.mapper.ProjectMapper;
import com.taskmanager.model.ProjectDTO;
import com.taskmanager.repository.ProjectRepository;
import com.taskmanager.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    private final ProjectMapper projectMapper;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
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
    @Transactional
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

    @Override
    @Transactional
    public String deleteProjectById(UUID id) {
        if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
            return id.toString();
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectDTO> search(String searchBy, String searchText) {
        return Collections.unmodifiableList(switch (searchBy) {
            case "projectId" -> searchByProjectId(searchText);
            case "projectName" -> searchByProjectName(searchText);
            case "dateOfStart" -> searchByDateOfStart(searchText);
            case "teamSize" -> searchByTeamSize(searchText);
            default -> this.findAll();
        });
    }

    private List<ProjectDTO> searchByProjectId(String searchText) {
        return projectRepository.findAllById(UUID.fromString(searchText.trim())).stream()
                .map(projectMapper::projectToProjectDto)
                .sorted(Comparator.comparing(ProjectDTO::getCreatedDate))
                .toList();
    }

    private List<ProjectDTO> searchByProjectName(String searchText) {
        return projectRepository.findAllByProjectNameContainingIgnoreCase(searchText.trim()).stream()
                .map(projectMapper::projectToProjectDto)
                .sorted(Comparator.comparing(ProjectDTO::getCreatedDate))
                .toList();
    }

    private List<ProjectDTO> searchByDateOfStart(String searchText) {
        return projectRepository
                .findAllByDateOfStart(LocalDate.from(FORMATTER.parse(searchText.trim()))).stream()
                .map(projectMapper::projectToProjectDto)
                .sorted(Comparator.comparing(ProjectDTO::getCreatedDate))
                .toList();
    }

    private List<ProjectDTO> searchByTeamSize(String searchText) {
        return projectRepository.findAllByTeamSize(Integer.parseInt(searchText.trim())).stream()
                .map(projectMapper::projectToProjectDto)
                .sorted(Comparator.comparing(ProjectDTO::getCreatedDate))
                .toList();
    }

}
