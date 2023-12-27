package com.taskmanager.repository;

import com.taskmanager.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ProjectRepository extends JpaRepository<Project, UUID> {

    List<Project> findAllById(UUID id);

    List<Project> findAllByProjectNameContainingIgnoreCase(String projectName);

    List<Project> findAllByDateOfStart(LocalDate dateOfStart);

    List<Project> findAllByTeamSize(Integer teamSize);

}
