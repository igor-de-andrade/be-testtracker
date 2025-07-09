package com.sevensystems.be_testtracker.config;

import com.sevensystems.be_testtracker.project.Project;
import com.sevensystems.be_testtracker.project.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.Arrays;

@Configuration
public class TestConfig implements CommandLineRunner {

    @Autowired
    public ProjectRepository projectRepository;

    @Override
    public void run(String... args) throws Exception {

        Project project1 = new Project(null, "ContEasy");
        Project project2 = new Project(null, "Pack Online");
        Project project3 = new Project(null, "eContador");

        projectRepository.saveAll(Arrays.asList(project1, project2, project3));
    }
}
