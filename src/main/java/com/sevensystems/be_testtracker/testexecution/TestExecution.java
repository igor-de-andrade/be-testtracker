package com.sevensystems.be_testtracker.testexecution;

import com.sevensystems.be_testtracker.testbattery.TestBattery;
import com.sevensystems.be_testtracker.testcase.TestCase;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "test_execution")
public class TestExecution {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Result result;

    private String comment;

    private String evidence;

    @ManyToOne
    @JoinColumn(name = "testcase_id")
    private TestCase testCase;

    @ManyToOne
    @JoinColumn(name = "testbattery_id")
    private TestBattery testBattery;

    public TestExecution() {

    }

    public TestExecution(UUID id, Result result, String comment, String evidence, TestCase testCase, TestBattery testBattery) {
        this.id = id;
        this.result = result;
        this.comment = comment;
        this.evidence = evidence;
        this.testCase = testCase;
        this.testBattery = testBattery;
    }

    public UUID getId() {
        return id;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getEvidence() {
        return evidence;
    }

    public void setEvidence(String evidence) {
        this.evidence = evidence;
    }

    public TestCase getTestCase() {
        return testCase;
    }

    public TestBattery getTestBattery() {
        return testBattery;
    }

}
