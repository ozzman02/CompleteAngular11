package com.taskmanager.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
public class ProjectDTO {

    private UUID id;

    @NotBlank
    @NotNull
    private String projectName;

    @NotNull
    private LocalDate dateOfStart;

    @NotNull
    private Integer teamSize;

    private Integer version;

    private LocalDateTime createdDate;

    private LocalDateTime updateDate;
}
