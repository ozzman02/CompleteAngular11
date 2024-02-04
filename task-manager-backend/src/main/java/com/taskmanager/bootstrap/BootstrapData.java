package com.taskmanager.bootstrap;

import com.taskmanager.entity.ClientLocation;
import com.taskmanager.entity.Project;
import com.taskmanager.entity.Role;
import com.taskmanager.entity.User;
import com.taskmanager.model.ProjectStatusEnum;
import com.taskmanager.repository.ClientLocationRepository;
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

    private final ClientLocationRepository clientLocationRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        loadClientLocationData();
        loadRolesData();
        loadUsersData();
        loadProjectData();
    }

    private void loadUsersData() {
        if (userRepository.count() == 0) {
            Role adminRole = roleRepository.findByName("ADMIN");
            Role employeeRole = roleRepository.findByName("EMPLOYEE");
            User admin = User.builder()
                    .username("osantamaria")
                    .firstName("Oscar")
                    .lastName("Santamaria")
                    .email("osantamaria@gmail.com")
                    .password(passwordEncoder.encode("adminTest"))
                    .roles(Collections.singletonList(adminRole))
                    .createdDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .build();
            User user = User.builder()
                    .username("davendano")
                    .firstName("Douglas")
                    .lastName("Avendano")
                    .email("davendano@gmail.com")
                    .password(passwordEncoder.encode("employeeTest"))
                    .roles(Collections.singletonList(employeeRole))
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
            Role employeeRole = Role.builder()
                    .name("EMPLOYEE")
                    .createdDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .build();
            roleRepository.saveAll(Arrays.asList(adminRole, employeeRole));
        }
    }

    private void loadProjectData() {
        if (projectRepository.count() == 0) {
            ClientLocation boston = clientLocationRepository.findByName("Boston");
            ClientLocation newDelhi = clientLocationRepository.findByName("New Delhi");
            ClientLocation newJersey = clientLocationRepository.findByName("New Jersey");
            ClientLocation newYork = clientLocationRepository.findByName("New York");
            ClientLocation london = clientLocationRepository.findByName("London");
            ClientLocation tokyo = clientLocationRepository.findByName("Tokyo");
            Project project1 = Project.builder()
                    .projectName("Front Management System")
                    .dateOfStart(LocalDate.now())
                    .createdDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .teamSize(10)
                    .active(Boolean.TRUE)
                    .status(ProjectStatusEnum.IN_FORCE)
                    .clientLocation(boston)
                    .build();
            Project project2 = Project.builder()
                    .projectName("Reporting Tool")
                    .dateOfStart(LocalDate.now())
                    .createdDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .teamSize(5)
                    .active(Boolean.TRUE)
                    .status(ProjectStatusEnum.SUPPORT)
                    .clientLocation(newDelhi)
                    .build();
            Project project3 = Project.builder()
                    .projectName("Hospital Management System")
                    .dateOfStart(LocalDate.now())
                    .createdDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .teamSize(20)
                    .active(Boolean.TRUE)
                    .status(ProjectStatusEnum.IN_FORCE)
                    .clientLocation(newJersey)
                    .build();
            Project project4 = Project.builder()
                    .projectName("Pharmacy Application")
                    .dateOfStart(LocalDate.now())
                    .createdDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .teamSize(15)
                    .active(Boolean.TRUE)
                    .status(ProjectStatusEnum.SUPPORT)
                    .clientLocation(newYork)
                    .build();
            Project project5 = Project.builder()
                    .projectName("Advanced Accounting System")
                    .dateOfStart(LocalDate.now())
                    .createdDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .teamSize(4)
                    .active(Boolean.TRUE)
                    .status(ProjectStatusEnum.IN_FORCE)
                    .clientLocation(london)
                    .build();
            Project project6 = Project.builder()
                    .projectName("Integration Management System")
                    .dateOfStart(LocalDate.now())
                    .createdDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .teamSize(4)
                    .active(Boolean.TRUE)
                    .status(ProjectStatusEnum.SUPPORT)
                    .clientLocation(tokyo)
                    .build();
            projectRepository.saveAll(Arrays.asList(project1, project2, project3, project4, project5, project6));
        }
    }

    private void loadClientLocationData() {
        if (clientLocationRepository.count() == 0) {
            ClientLocation boston = ClientLocation.builder()
                    .name("Boston")
                    .build();
            ClientLocation newDelhi = ClientLocation.builder()
                    .name("New Delhi")
                    .build();
            ClientLocation newJersey = ClientLocation.builder()
                    .name("New Jersey")
                    .build();
            ClientLocation newYork = ClientLocation.builder()
                    .name("New York")
                    .build();
            ClientLocation london = ClientLocation.builder()
                    .name("London")
                    .build();
            ClientLocation tokyo = ClientLocation.builder()
                    .name("Tokyo")
                    .build();
            clientLocationRepository.saveAll(Arrays.asList(boston, newDelhi, newJersey, newYork, london, tokyo));
        }
    }
}
