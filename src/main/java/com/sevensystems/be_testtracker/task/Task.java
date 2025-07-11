package com.sevensystems.be_testtracker.task;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sevensystems.be_testtracker.project.Project;
import com.sevensystems.be_testtracker.testcase.TestCase;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
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

    @JsonIgnore
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TestCase> testCases = new ArrayList<>();

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
