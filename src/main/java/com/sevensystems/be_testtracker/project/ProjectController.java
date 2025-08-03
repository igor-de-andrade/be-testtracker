package com.sevensystems.be_testtracker.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {

    @Autowired
    public ProjectService service;

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> findAll() {
        List<ProjectDTO> projects = service.findAll();
        return ResponseEntity.ok().body(projects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> findById(@PathVariable UUID id) {
        ProjectDTO project = service.findById(id);
        return ResponseEntity.ok().body(project);
    }

    @PostMapping()
    public ResponseEntity<ProjectDTO> insert(@RequestBody ProjectDTO obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectDTO> update(@PathVariable UUID id, @RequestBody ProjectDTO obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
