package com.sevensystems.be_testtracker.testbattery;

import com.sevensystems.be_testtracker.task.Task;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "test_battery")
public class TestBattery {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String description;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    public TestBattery() {
    }

    public TestBattery(UUID id, String description, Task task) {
        this.id = id;
        this.description = description;
        this.task = task;
    }

    public UUID getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Task getTask() {
        return task;
    }
}
