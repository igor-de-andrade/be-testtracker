package com.sevensystems.be_testtracker.tag;

import com.sevensystems.be_testtracker.project.Project;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private Color color;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public Tag() {

    }

    public Tag(UUID id, String name, Color color, Project project) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.project = project;
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Project getProject() {
        return project;
    }

}
