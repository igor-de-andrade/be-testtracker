package com.sevensystems.be_testtracker.testexecution;

import com.sevensystems.be_testtracker.testcase.TestCase;
import com.sevensystems.be_testtracker.testcase.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/testexecution")
public class TestExecutionController {

    @Autowired
    public TestExecutionService service;

    @GetMapping
    public ResponseEntity<List<TestExecution>> findAll() {
        List<TestExecution> testExecutions = service.findAll();
        return ResponseEntity.ok().body(testExecutions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestExecution> findById(@PathVariable UUID id) {
        TestExecution testExecution = service.findById(id);
        return ResponseEntity.ok().body(testExecution);
    }

    @PostMapping()
    public ResponseEntity<TestExecution> insert(@RequestBody TestExecution obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TestExecution> update(@PathVariable UUID id, @RequestBody TestExecution obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
