package com.sevensystems.be_testtracker.testcase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/testcase")
public class TestCaseController {

    @Autowired
    public TestCaseService service;

    @GetMapping
    public ResponseEntity<List<TestCase>> findAll() {
        List<TestCase> testCases = service.findAll();
        return ResponseEntity.ok().body(testCases);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestCase> findById(@PathVariable UUID id) {
        TestCase testCase = service.findById(id);
        return ResponseEntity.ok().body(testCase);
    }

    @PostMapping()
    public ResponseEntity<TestCase> insert(@RequestBody TestCase obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TestCase> update(@PathVariable UUID id, @RequestBody TestCase obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
