package com.taskmanager.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.taskmanager.model.ProjectStatusEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "projects")
public class Project implements Serializable {

    @Serial
    private static final long serialVersionUID = 564990452662589965L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID id;

    @NotBlank
    @NotNull
    @Column
    private String projectName;

    @NotNull
    @Column
    private LocalDate dateOfStart;

    @NotNull
    @Column
    private Integer teamSize;

    @Column
    private Boolean active;

    @NotNull
    @Column
    @Enumerated(EnumType.STRING)
    private ProjectStatusEnum status;

    @ManyToOne
    @JsonManagedReference
    private ClientLocation clientLocation;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime updateDate;

    @Version
    @NotNull
    private Integer version;

}
