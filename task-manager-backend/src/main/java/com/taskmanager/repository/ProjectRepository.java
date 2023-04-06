package com.taskmanager.repository;

import com.taskmanager.model.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface ProjectRepository extends CrudRepository<Project, Long> {
}
