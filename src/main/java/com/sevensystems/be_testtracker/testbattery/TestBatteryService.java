package com.sevensystems.be_testtracker.testbattery;

import com.sevensystems.be_testtracker.exception.NotFoundException;
import com.sevensystems.be_testtracker.task.Task;
import com.sevensystems.be_testtracker.task.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TestBatteryService {

    @Autowired
    private TestBatteryRepository repository;

    public List<TestBattery> findAll() {
        return repository.findAll();
    }

    public TestBattery findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não foi encontrada nenhuma bateria de testes com o ID: " + id));
    }

    public TestBattery insert(TestBattery obj) {
        TestBattery testBattery = new TestBattery(null, obj.getDescription(), obj.getTask());
        testBattery = repository.save(testBattery);

        return testBattery;
    }

    public void delete(UUID id) {
        repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não foi encontrada nenhuma bateria de testes com o ID: " + id));

        repository.deleteById(id);
    }

}
