package com.sevensystems.be_testtracker.project;

import java.util.UUID;

public class ProjectDTO {

    private UUID id;
    private String name;
    private String identifier;

    public ProjectDTO() {

    }

    public ProjectDTO(UUID id, String name, String identifier) {
        this.id = id;
        this.name = name;
        this.identifier = identifier;
    }

    public ProjectDTO(Project project) {
        this.id = project.getId();
        this.name = project.getName();
        this.identifier = project.getIdentifier();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public ProjectDTO format() {
        if (this.name != null) {
            this.name = this.name.trim();
        }
        if (this.getIdentifier() != null) {
            this.identifier = this.identifier.toUpperCase();
        }
        return this;
    }
}
