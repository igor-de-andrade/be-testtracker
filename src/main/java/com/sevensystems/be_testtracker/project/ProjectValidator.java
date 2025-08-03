package com.sevensystems.be_testtracker.project;

import com.sevensystems.be_testtracker.exception.ValidationException;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class ProjectValidator {

    @Autowired
    ProjectRepository projectRepository;

    public void create(ProjectDTO project) throws ValidationException {
        Map<String, String> errors = new HashMap<>();
        generalValidator(project, errors);
        validateIdentifier(project, errors);

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }

    }

    public void update(ProjectDTO project) throws ValidationException {
        Map<String, String> errors = new HashMap<>();
        generalValidator(project, errors);

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }

    public void generalValidator(ProjectDTO project, Map<String, String> errors) {
        validateName(project, errors);
    }

    public void validateName(ProjectDTO project, Map<String, String> errors) {
        String name = project.getName();

        if(StringUtils.isBlank(name)) {
           errors.put("name", "O nome é obrigatório.");
           return;
        }

        if(name.length() < 3 || name.length() > 50) {
            errors.put("name", "O nome deve possuir entre 3 e 50 caracteres.");
        }
    }

    public void validateIdentifier(ProjectDTO project, Map<String, String> errors) {
        String identifier = project.getIdentifier();

        if(StringUtils.isBlank(identifier)) {
            errors.put("identifier", "O identificador é obrigatório.");
            return;
        }

        if(identifier.length() < 3 || identifier.length() > 5) {
            errors.put("identifier", "O identificador deve possuir entre 3 e 5 caracteres.");
        }

        if(!identifier.matches("^[A-Z]+$")) {
            errors.put("identifier", "O identificador deve possuir apenas caracteres de A-Z.");
        }

        List<Project> projects = projectRepository.findByIdentifier(identifier);
        if(!projects.isEmpty()) {
            errors.put("identifier", "O identificador '" + identifier + "' já está sendo utilizado.");
        }
    }
}
