package com.sevensystems.be_testtracker.testexecution;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TestExecutionRepository extends JpaRepository<TestExecution, UUID> {
}
