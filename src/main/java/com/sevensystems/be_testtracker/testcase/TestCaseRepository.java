package com.sevensystems.be_testtracker.testcase;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TestCaseRepository extends JpaRepository<TestCase, UUID> {
}
