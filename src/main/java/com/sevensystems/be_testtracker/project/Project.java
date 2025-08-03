package com.sevensystems.be_testtracker.project;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sevensystems.be_testtracker.tag.Tag;
import com.sevensystems.be_testtracker.task.Task;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String identifier;

    @JsonIgnore
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tag> tags = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();

    public Project() {
    }

    public Project(UUID id, String name, String identifier) {
        this.id = id;
        this.name = name;
        this.identifier = identifier;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String name) {
        this.identifier = identifier;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
