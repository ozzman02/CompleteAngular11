package com.taskmanager.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class ClientLocationDTO {

    private UUID id;

    @NotBlank
    @NotNull
    private String name;

}
