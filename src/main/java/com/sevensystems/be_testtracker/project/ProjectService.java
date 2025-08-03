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

    @Autowired
    private ProjectValidator validator;

    public List<ProjectDTO> findAll() {
        List<Project> projects = repository.findAll();
        return projects.stream().map(ProjectDTO::new).toList();
    }

    public ProjectDTO findById(UUID id) {
        Project project = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não foi encontrado nenhum projeto com o ID: " + id));
        return new ProjectDTO(project);
    }

    public List<ProjectDTO> findByIdentifier(String identifier) {
        List<Project> projects = repository.findByIdentifier(identifier);
        return projects.stream().map(ProjectDTO::new).toList();
    }

    public ProjectDTO insert(ProjectDTO obj) {
        obj.format();

        validator.create(obj);
        Project project = new Project(null, obj.getName(), obj.getIdentifier());
        project = repository.save(project);

        return new ProjectDTO(project);
    }

    public ProjectDTO update(UUID id, ProjectDTO obj) {
        obj.format();
        validator.update(obj);

        Project entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não foi encontrado nenhum projeto com o ID: " + id));
        updateData(entity, obj);
        entity = repository.save(entity);

        return new ProjectDTO(entity);
    }

    public void delete(UUID id) {
        repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não foi encontrado nenhum projeto com o ID: " + id));

        repository.deleteById(id);
    }

    private void updateData(Project entity, ProjectDTO obj) {
        entity.setName(obj.getName());
    }


}
