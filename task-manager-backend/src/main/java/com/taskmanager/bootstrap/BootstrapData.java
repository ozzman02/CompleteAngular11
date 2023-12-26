package com.taskmanager.bootstrap;

import com.taskmanager.entity.Project;
import com.taskmanager.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final ProjectRepository projectRepository;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        loadProjectData();
    }

    private void loadProjectData() {
        if (projectRepository.count() == 0) {
            Project project1 = Project.builder()
                    .projectName("Front Management System")
                    .dateOfStart(LocalDate.now())
                    .createdDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .teamSize(10)
                    .build();
            Project project2 = Project.builder()
                    .projectName("Reporting Tool")
                    .dateOfStart(LocalDate.now())
                    .createdDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .teamSize(5)
                    .build();
            Project project3 = Project.builder()
                    .projectName("Hospital Management System")
                    .dateOfStart(LocalDate.now())
                    .createdDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .teamSize(20)
                    .build();
            Project project4 = Project.builder()
                    .projectName("Pharmacy Application")
                    .dateOfStart(LocalDate.now())
                    .createdDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .teamSize(15)
                    .build();
            Project project5 = Project.builder()
                    .projectName("Advanced Accounting System")
                    .dateOfStart(LocalDate.now())
                    .createdDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .teamSize(4)
                    .build();
            projectRepository.saveAll(Arrays.asList(project1, project2, project3, project4, project5));
        }
    }
}
