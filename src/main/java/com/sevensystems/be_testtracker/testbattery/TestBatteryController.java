package com.sevensystems.be_testtracker.testbattery;

import com.sevensystems.be_testtracker.task.Task;
import com.sevensystems.be_testtracker.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/testbattery")
public class TestBatteryController {

    @Autowired
    public TestBatteryService service;

    @GetMapping
    public ResponseEntity<List<TestBattery>> findAll() {
        List<TestBattery> testBatteries = service.findAll();
        return ResponseEntity.ok().body(testBatteries);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestBattery> findById(@PathVariable UUID id) {
        TestBattery testBattery = service.findById(id);
        return ResponseEntity.ok().body(testBattery);
    }

    @PostMapping()
    public ResponseEntity<TestBattery> insert(@RequestBody TestBattery obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
