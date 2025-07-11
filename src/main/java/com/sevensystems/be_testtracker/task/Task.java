package com.sevensystems.be_testtracker.task;

import com.sevensystems.be_testtracker.project.Project;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String code;

    private String description;

    private Status status;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public Task() {

    }

    public Task(UUID id, String code, String description, Status status, Project project) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.status = status;
        this.project = project;
    }

    public UUID getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Project getProject() {
        return project;
    }
}
