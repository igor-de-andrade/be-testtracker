package com.sevensystems.be_testtracker.tag;

import com.sevensystems.be_testtracker.exception.ValidationException;
import com.sevensystems.be_testtracker.project.ProjectRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class TagValidator {
    @Autowired
    TagRepository tagRepository;

    @Autowired
    ProjectRepository projectRepository;

    public void create(TagDTO tagDTO) throws ValidationException {
        Map<String, String> errors = new HashMap<>();
        generalValidator(tagDTO, errors);
        validateProjectId(tagDTO, errors);


        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }

    }

    public void update(TagDTO tagDTO) throws ValidationException {
        Map<String, String> errors = new HashMap<>();
        generalValidator(tagDTO, errors);

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }

    public void generalValidator(TagDTO tagDTO, Map<String, String> errors) {
        validateName(tagDTO, errors);
        validateColor(tagDTO, errors);
    }

    public void validateName(TagDTO tagDTO, Map<String, String> errors) {
        String name = tagDTO.getName();

        if(StringUtils.isBlank(name)) {
            errors.put("name", "O nome é obrigatório.");
            return;
        }

        if(name.length() < 3 || name.length() > 20) {
            errors.put("name", "O nome deve possuir entre 3 e 20 caracteres.");
        }
    }

    public void validateColor(TagDTO tagDTO, Map<String, String> errors) {
        String color = tagDTO.getColor();

        if (StringUtils.isBlank(color)) {
            errors.put("color", "A cor é obrigatória.");
            return;
        }

        String acceptedColors = Arrays.stream(Color.values())
                .map(Enum::name)
                .collect(Collectors.joining(", "));

        try {
            Color.valueOf(color);
        } catch (IllegalArgumentException e) {
            errors.put("color", "Cor inválida. (Valores válidos: " + acceptedColors + ")");
        }
    }

    public void validateProjectId(TagDTO tagDTO, Map<String, String> errors) {
        UUID projectId = tagDTO.getProjectId();

        if (projectId == null) {
            errors.put("projectId", "O ID do projeto é obrigatório.");
        }

    }
}
