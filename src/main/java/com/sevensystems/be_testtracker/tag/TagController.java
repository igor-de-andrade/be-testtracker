package com.sevensystems.be_testtracker.tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tag")
public class TagController {

    @Autowired
    public TagService service;

    @GetMapping
    public ResponseEntity<List<Tag>> findAll() {
        List<Tag> tags = service.findAll();
        return ResponseEntity.ok().body(tags);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tag> findById(@PathVariable UUID id) {
        Tag tag = service.findById(id);
        return ResponseEntity.ok().body(tag);
    }

    @PostMapping()
    public ResponseEntity<Tag> insert(@RequestBody TagDTO obj) {
        Tag tag = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(tag);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tag> update(@PathVariable UUID id, @RequestBody TagDTO obj) {
        Tag tag = service.update(id, obj);
        return ResponseEntity.ok().body(tag);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
