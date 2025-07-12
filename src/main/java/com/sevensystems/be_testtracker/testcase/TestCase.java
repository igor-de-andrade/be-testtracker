package com.sevensystems.be_testtracker.testcase;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sevensystems.be_testtracker.task.Status;
import com.sevensystems.be_testtracker.task.Task;
import com.sevensystems.be_testtracker.testexecution.TestExecution;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "test_case")
public class TestCase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String description;

    private String preCondition;

    private String expectedResult;

    private Type type;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @JsonIgnore
    @OneToMany(mappedBy = "testCase", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TestExecution> testExecutions = new ArrayList<>();

    public TestCase() {

    }

    public TestCase(UUID id, String description, String preCondition, String expectedResult, Type type, Task task) {
        this.id = id;
        this.description = description;
        this.preCondition = preCondition;
        this.expectedResult = expectedResult;
        this.type = type;
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

    public String getPreCondition() {
        return preCondition;
    }

    public void setPreCondition(String preCondition) {
        this.preCondition = preCondition;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Task getTask() {
        return task;
    }

    public List<TestExecution> getTestExecutions() {
        return testExecutions;
    }
}
