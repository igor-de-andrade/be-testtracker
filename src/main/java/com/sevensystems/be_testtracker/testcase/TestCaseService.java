package com.sevensystems.be_testtracker.testcase;

import com.sevensystems.be_testtracker.exception.NotFoundException;
import com.sevensystems.be_testtracker.task.Task;
import com.sevensystems.be_testtracker.task.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TestCaseService {

    @Autowired
    private TestCaseRepository repository;

    public List<TestCase> findAll() {
        return repository.findAll();
    }

    public TestCase findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não foi encontrado nenhum caso de teste com o ID: " + id));
    }

    public TestCase insert(TestCase obj) {
        TestCase testCase = new TestCase(null, obj.getDescription(), obj.getPreCondition(), obj.getExpectedResult(), obj.getType(), obj.getTask());
        testCase = repository.save(testCase);

        return testCase;
    }

    public TestCase update(UUID id, TestCase obj) {
        TestCase entity = this.findById(id);
        updateData(entity, obj);
        entity = repository.save(entity);

        return entity;
    }

    public void delete(UUID id) {
        repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não foi encontrado nenhum caso de teste com o ID: " + id));

        repository.deleteById(id);
    }

    private void updateData(TestCase entity, TestCase obj) {
        entity.setDescription(obj.getDescription());
        entity.setPreCondition(obj.getPreCondition());
        entity.setExpectedResult(obj.getExpectedResult());
        entity.setType(obj.getType());
    }
}
