package com.sevensystems.be_testtracker.config;

import com.sevensystems.be_testtracker.project.Project;
import com.sevensystems.be_testtracker.project.ProjectRepository;
import com.sevensystems.be_testtracker.tag.Color;
import com.sevensystems.be_testtracker.tag.Tag;
import com.sevensystems.be_testtracker.tag.TagRepository;
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

    @Override
    public void run(String... args) throws Exception {

        Project project1 = new Project(null, "ContEasy");
        Project project2 = new Project(null, "Pack Online");
        Project project3 = new Project(null, "eContador");
        projectRepository.saveAll(Arrays.asList(project1, project2, project3));

        Tag tag1 = new Tag(null, "Cadastros", Color.BLUE, null);
        Tag tag2 = new Tag(null, "Autenticação", Color.RED, null);
        tagRepository.saveAll(Arrays.asList(tag1, tag2));
    }
}
