package com.sevensystems.be_testtracker.tag;

import com.sevensystems.be_testtracker.exception.NotFoundException;
import com.sevensystems.be_testtracker.project.Project;
import com.sevensystems.be_testtracker.project.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TagService {

    @Autowired
    private TagRepository repository;
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TagValidator tagValidator;

    public List<Tag> findAll() {
        return repository.findAll();
    }

    public Tag findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não foi encontrada nenhuma tag com o ID: " + id));
    }

    public Tag insert(TagDTO obj) {
        obj.format();
        tagValidator.create(obj);

        Color color = Color.valueOf(obj.getColor().toUpperCase());

        Project project = projectRepository.findById(obj.getProjectId())
                .orElseThrow(() -> new NotFoundException("Não foi encontrado nenhum projeto com o ID: " + obj.getProjectId()));

        Tag tag = new Tag(null, obj.getName(), color, project);
        tag = repository.save(tag);

        return tag;
    }

    public Tag update(UUID id, TagDTO obj) {
        obj.format();
        tagValidator.update(obj);

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

    private void updateData(Tag entity, TagDTO obj) {
        Color color = Color.valueOf(obj.getColor().toUpperCase());
        entity.setName(obj.getName());
        entity.setColor(color);
    }
}
