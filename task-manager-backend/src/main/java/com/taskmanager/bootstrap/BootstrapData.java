package com.taskmanager.bootstrap;

import com.taskmanager.entity.Project;
import com.taskmanager.entity.Role;
import com.taskmanager.entity.User;
import com.taskmanager.repository.ProjectRepository;
import com.taskmanager.repository.RoleRepository;
import com.taskmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final ProjectRepository projectRepository;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        loadProjectData();
        loadRolesData();
        loadUsersData();
    }

    private void loadUsersData() {
        if (userRepository.count() == 0) {
            Role adminRole = roleRepository.findByName("ADMIN");
            Role userRole = roleRepository.findByName("USER");
            User admin = User.builder()
                    .username("osantamaria")
                    .firstName("Oscar")
                    .lastName("Santamaria")
                    .email("osantamaria@gmail.com")
                    //.password("adminTest")
                    .password(passwordEncoder.encode("adminTest"))
                    .roles(Arrays.asList(adminRole, userRole))
                    .createdDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .build();
            User user = User.builder()
                    .username("davendano")
                    .firstName("Douglas")
                    .lastName("Avendano")
                    .email("davendano@gmail.com")
                    //.password("userTest")
                    .password(passwordEncoder.encode("userTest"))
                    .roles(Collections.singletonList(userRole))
                    .createdDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .build();
            userRepository.saveAll(Arrays.asList(admin, user));
        }
    }

    private void loadRolesData() {
        if (roleRepository.count() == 0) {
            Role adminRole = Role.builder()
                    .name("ADMIN")
                    .createdDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .build();
            Role userRole = Role.builder()
                    .name("USER")
                    .createdDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .build();
            roleRepository.saveAll(Arrays.asList(adminRole, userRole));
        }
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
