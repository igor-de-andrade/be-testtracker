package com.sevensystems.be_testtracker.config;

import com.sevensystems.be_testtracker.project.Project;
import com.sevensystems.be_testtracker.project.ProjectRepository;
import com.sevensystems.be_testtracker.tag.Color;
import com.sevensystems.be_testtracker.tag.Tag;
import com.sevensystems.be_testtracker.tag.TagRepository;
import com.sevensystems.be_testtracker.task.Status;
import com.sevensystems.be_testtracker.task.Task;
import com.sevensystems.be_testtracker.task.TaskRepository;
import com.sevensystems.be_testtracker.testbattery.TestBattery;
import com.sevensystems.be_testtracker.testbattery.TestBatteryRepository;
import com.sevensystems.be_testtracker.testcase.TestCase;
import com.sevensystems.be_testtracker.testcase.TestCaseRepository;
import com.sevensystems.be_testtracker.testcase.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.Arrays;

@Configuration
public class TestConfig implements CommandLineRunner {

    @Autowired
    public ProjectRepository projectRepository;

    @Autowired
    public TagRepository tagRepository;

    @Autowired
    public TaskRepository taskRepository;

    @Autowired
    public TestCaseRepository testCaseRepository;

    @Autowired
    public TestBatteryRepository testBatteryRepository;

    @Override
    public void run(String... args) throws Exception {

        Project project1 = new Project(null, "ContEasy");
        Project project2 = new Project(null, "Pack Online");
        Project project3 = new Project(null, "eContador");
        projectRepository.saveAll(Arrays.asList(project1, project2, project3));

        Tag tag1 = new Tag(null, "Cadastros", Color.BLUE, null);
        Tag tag2 = new Tag(null, "Autenticação", Color.RED, null);
        tagRepository.saveAll(Arrays.asList(tag1, tag2));

        Task task1 = new Task(null, "PKW-48957", "Ajustar cor do botão", Status.TO_DO, null);
        Task task2 = new Task(null, "PKW-49756", "Ajustar redirecionamento", Status.COMPLETED, null);
        taskRepository.saveAll(Arrays.asList(task1, task2));

        TestCase testCase1 = new TestCase(null, "Campos obrigatórios", null, null, Type.FUNCTIONAL, null);
        TestCase testCase2 = new TestCase(null, "Recuperação de dados nulos", null, null, Type.FUNCTIONAL, null);
        testCaseRepository.saveAll(Arrays.asList(testCase1, testCase2));

        TestBattery testBattery1 = new TestBattery(null, "1ª bateria", null);
        TestBattery testBattery2 = new TestBattery(null, "2ª bateria", null);
        testBatteryRepository.saveAll(Arrays.asList(testBattery1, testBattery2));
    }
}
