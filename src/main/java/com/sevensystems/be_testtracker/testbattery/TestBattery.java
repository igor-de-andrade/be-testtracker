package com.sevensystems.be_testtracker.testbattery;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sevensystems.be_testtracker.task.Task;
import com.sevensystems.be_testtracker.testexecution.TestExecution;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
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

    @JsonIgnore
    @OneToMany(mappedBy = "testBattery", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TestExecution> testExecutions = new ArrayList<>();

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

    public List<TestExecution> getTestExecutions() {
        return testExecutions;
    }
}
