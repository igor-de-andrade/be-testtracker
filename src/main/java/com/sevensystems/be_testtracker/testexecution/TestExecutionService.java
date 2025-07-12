package com.sevensystems.be_testtracker.testexecution;

import com.sevensystems.be_testtracker.exception.NotFoundException;
import com.sevensystems.be_testtracker.testcase.TestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TestExecutionService {

    @Autowired
    private TestExecutionRepository repository;

    public List<TestExecution> findAll() {
        return repository.findAll();
    }

    public TestExecution findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não foi encontrada nenhuma execução de teste com o ID: " + id));
    }

    public TestExecution insert(TestExecution obj) {
        TestExecution testExecution = new TestExecution(null, obj.getResult(), obj.getComment(), obj.getEvidence(), obj.getTestCase(), obj.getTestBattery());
        testExecution = repository.save(testExecution);

        return testExecution;
    }

    public TestExecution update(UUID id, TestExecution obj) {
        TestExecution entity = this.findById(id);
        updateData(entity, obj);
        entity = repository.save(entity);

        return entity;
    }

    public void delete(UUID id) {
        repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não foi encontrada nenhuma execução de teste com o ID: " + id));

        repository.deleteById(id);
    }

    private void updateData(TestExecution entity, TestExecution obj) {
        entity.setResult(obj.getResult());
        entity.setComment(obj.getComment());
        entity.setEvidence(obj.getEvidence());
    }

}
