package com.sevensystems.be_testtracker.testbattery;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TestBatteryRepository extends JpaRepository<TestBattery, UUID> {
}
