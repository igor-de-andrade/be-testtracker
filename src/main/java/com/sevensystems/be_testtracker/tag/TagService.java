package com.sevensystems.be_testtracker.tag;

import com.sevensystems.be_testtracker.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TagService {

    @Autowired
    private TagRepository repository;

    public List<Tag> findAll() {
        return repository.findAll();
    }

    public Tag findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não foi encontrada nenhuma tag com o ID: " + id));
    }

    public Tag insert(Tag obj) {
        Tag tag = new Tag(null, obj.getName(), obj.getColor(), obj.getProject());
        tag = repository.save(tag);

        return tag;
    }

    public Tag update(UUID id, Tag obj) {
        Tag entity = this.findById(id);
        updateData(entity, obj);
        entity = repository.save(entity);

        return entity;
    }

    public void delete(UUID id) {
        repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não foi encontrada nenhuma tag com o ID: " + id));

        repository.deleteById(id);
    }

    private void updateData(Tag entity, Tag obj) {
        entity.setName(obj.getName());
        entity.setColor(obj.getColor());
    }
}
