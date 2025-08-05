package com.sevensystems.be_testtracker.tag;

import java.util.UUID;

public class TagDTO {

    private UUID id;
    private String name;
    private String color;
    private UUID projectId;

    public TagDTO() {
    }

    public TagDTO(UUID id, String name, String color, UUID projectId) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.projectId = projectId;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public UUID getProjectId() {
        return projectId;
    }

    public void setProjectId(UUID projectId) {
        this.projectId = projectId;
    }

    public TagDTO format() {
        if (this.name != null) {
            this.name = this.name.trim();
        }
        if (this.color != null) {
            this.color = this.color.trim().toUpperCase();
        }
        return this;
    }
}
