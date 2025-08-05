package com.sevensystems.be_testtracker.tag;

import com.sevensystems.be_testtracker.exception.NotFoundException;
import com.sevensystems.be_testtracker.exception.ValidationException;
import com.sevensystems.be_testtracker.project.Project;
import com.sevensystems.be_testtracker.project.ProjectDTO;
import com.sevensystems.be_testtracker.project.ProjectRepository;
import com.sevensystems.be_testtracker.project.ProjectService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

//@ActiveProfiles("test")
@SpringBootTest
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TagServiceTest {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TagService tagService;


    private Project project;

    @BeforeEach
    void setup() {
        tagRepository.deleteAll();
        projectRepository.deleteAll();

        project = new Project(null, "ContEasy", "CTE");
        project = projectRepository.save(project);
    }

    TagDTO createTagDTO() {
        return new TagDTO(null, "Cadastros", "BLUE", project.getId());
    }

    @Test
    @DisplayName("Deve cadastrar uma tag com sucesso")
    void Case1() {
        TagDTO tagDTO = createTagDTO();

        Tag result = tagService.insert(tagDTO);

        assertNotNull(result.getId());
        assertEquals("Cadastros", result.getName());
        assertEquals(Color.BLUE, result.getColor());

        Optional<Tag> databaseObject = tagRepository.findById(result.getId());
        assertTrue(databaseObject.isPresent());
    }

    @Test
    @DisplayName("Não deve cadastrar uma tag quando o nome for nulo")
    void Case2() {
        TagDTO tagDTO = createTagDTO();
        tagDTO.setName(null);


        ValidationException exception = assertThrows(
                ValidationException.class,
                () -> tagService.insert(tagDTO)
        );

        Map<String, String> errors = exception.getErrors();

        assertTrue(errors.containsKey("name"));
        assertEquals("O nome é obrigatório.", errors.get("name"));
    }

    @Test
    @DisplayName("Não deve cadastrar uma tag quando o nome for vazio")
    void Case3() {
        TagDTO tagDTO = createTagDTO();
        tagDTO.setName("");

        ValidationException exception = assertThrows(
                ValidationException.class,
                () -> tagService.insert(tagDTO)
        );

        Map<String, String> errors = exception.getErrors();

        assertTrue(errors.containsKey("name"));
        assertEquals("O nome é obrigatório.", errors.get("name"));
    }

    @Test
    @DisplayName("Não deve cadastrar uma tag quando o nome for inválido")
    void Case4() {
        TagDTO tagDTO = createTagDTO();
        tagDTO.setName("  ");

        ValidationException exception = assertThrows(
                ValidationException.class,
                () -> tagService.insert(tagDTO)
        );

        Map<String, String> errors = exception.getErrors();

        assertTrue(errors.containsKey("name"));
        assertEquals("O nome é obrigatório.", errors.get("name"));
    }

    @Test
    @DisplayName("Não deve cadastrar uma tag quando o nome for menor que 3 caracteres")
    void Case5() {
        TagDTO tagDTO = createTagDTO();
        tagDTO.setName("AB");

        ValidationException exception = assertThrows(
                ValidationException.class,
                () -> tagService.insert(tagDTO)
        );

        Map<String, String> errors = exception.getErrors();

        assertTrue(errors.containsKey("name"));
        assertEquals("O nome deve possuir entre 3 e 20 caracteres.", errors.get("name"));
    }

    @Test
    @DisplayName("Não deve cadastrar uma tag quando o nome for maior que 20 caracteres")
    void Case6() {
        TagDTO tagDTO = createTagDTO();
        tagDTO.setName("Lorem ipsum dolor sit");

        ValidationException exception = assertThrows(
                ValidationException.class,
                () -> tagService.insert(tagDTO)
        );

        Map<String, String> errors = exception.getErrors();

        assertTrue(errors.containsKey("name"));
        assertEquals("O nome deve possuir entre 3 e 20 caracteres.", errors.get("name"));
    }

    @Test
    @DisplayName("Não deve cadastrar uma tag quando a cor for nula")
    void Case7() {
        TagDTO tagDTO = createTagDTO();
        tagDTO.setColor(null);

        ValidationException exception = assertThrows(
                ValidationException.class,
                () -> tagService.insert(tagDTO)
        );

        Map<String, String> errors = exception.getErrors();

        assertTrue(errors.containsKey("color"));
        assertEquals("A cor é obrigatória.", errors.get("color"));
    }

    @Test
    @DisplayName("Não deve cadastrar uma tag quando a cor for vazia")
    void Case8() {
        TagDTO tagDTO = createTagDTO();
        tagDTO.setColor("");

        ValidationException exception = assertThrows(
                ValidationException.class,
                () -> tagService.insert(tagDTO)
        );

        Map<String, String> errors = exception.getErrors();

        assertTrue(errors.containsKey("color"));
        assertEquals("A cor é obrigatória.", errors.get("color"));
    }

    @Test
    @DisplayName("Não deve cadastrar uma tag quando a cor for inválida")
    void Case9() {
        TagDTO tagDTO = createTagDTO();
        tagDTO.setColor("BROWN");

        ValidationException exception = assertThrows(
                ValidationException.class,
                () -> tagService.insert(tagDTO)
        );

        Map<String, String> errors = exception.getErrors();

        assertTrue(errors.containsKey("color"));
        assertEquals("Cor inválida. (Valores válidos: BLUE, GREEN, GREY, ORANGE, PINK, PURPLE, RED, YELLOW)", errors.get("color"));
    }

    @Test
    @DisplayName("Não deve cadastrar uma tag quando o projeto for nulo")
    void Case10() {
        TagDTO tagDTO = createTagDTO();
        tagDTO.setProjectId(null);

        ValidationException exception = assertThrows(
                ValidationException.class,
                () -> tagService.insert(tagDTO)
        );

        Map<String, String> errors = exception.getErrors();

        assertTrue(errors.containsKey("projectId"));
        assertEquals("O ID do projeto é obrigatório.", errors.get("projectId"));
    }

    @Test
    @DisplayName("Não deve cadastrar uma tag quando o projeto não for encontrado")
    void Case11() {
        TagDTO tagDTO = createTagDTO();
        String invalidUUID = "18b987d3-a4f5-4e64-9ff8-714aa1c6db21";
        tagDTO.setProjectId(UUID.fromString(invalidUUID));


        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> tagService.insert(tagDTO)
        );

        assertEquals("Não foi encontrado nenhum projeto com o ID: " + invalidUUID, exception.getMessage());

    }

    @Test
    @DisplayName("Deve editar uma tag com sucesso")
    void Case12() {
        TagDTO tagDTO = createTagDTO();

        Tag result = tagService.insert(tagDTO);
        tagDTO.setName("Configurações");
        result = tagService.update(result.getId(), tagDTO);

        assertEquals("Configurações", result.getName());
        assertEquals(Color.BLUE, result.getColor());

        Optional<Tag> databaseObject = tagRepository.findById(result.getId());
        assertTrue(databaseObject.isPresent());
    }

}
