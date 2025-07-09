package com.sevensystems.be_testtracker.project;

import com.sevensystems.be_testtracker.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository repository;

    public List<Project> findAll() {
        return repository.findAll();
    }

    public Project findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não foi encontrado nenhum projeto com o ID: " + id));
    }

    public Project insert(Project obj) {
        Project project = new Project(null, obj.getName());
        project = repository.save(project);

        return project;
    }

    public Project update(UUID id, Project obj) {
        Project entity = this.findById(id);
        updateData(entity, obj);
        entity = repository.save(entity);

        return entity;
    }

    public void delete(UUID id) {
        repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não foi encontrado nenhum projeto com o ID: " + id));

        repository.deleteById(id);
    }

    private void updateData(Project entity, Project obj) {
        entity.setName(obj.getName());
    }


}
