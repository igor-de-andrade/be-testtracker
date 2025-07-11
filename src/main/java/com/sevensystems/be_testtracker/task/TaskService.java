package com.sevensystems.be_testtracker.task;

import com.sevensystems.be_testtracker.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    public List<Task> findAll() {
        return repository.findAll();
    }

    public Task findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não foi encontrada nenhuma tarefa com o ID: " + id));
    }

    public Task insert(Task obj) {
        Task task = new Task(null, obj.getCode(), obj.getDescription(), obj.getStatus(), obj.getProject());
        task = repository.save(task);

        return task;
    }

    public Task update(UUID id, Task obj) {
        Task entity = this.findById(id);
        updateData(entity, obj);
        entity = repository.save(entity);

        return entity;
    }

    public void delete(UUID id) {
        repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não foi encontrada nenhuma tarefa com o ID: " + id));

        repository.deleteById(id);
    }

    private void updateData(Task entity, Task obj) {
        entity.setCode(obj.getCode());
        entity.setDescription(obj.getDescription());
        entity.setStatus(obj.getStatus());
    }

}
