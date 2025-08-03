package com.sevensystems.be_testtracker.project;

import com.sevensystems.be_testtracker.exception.ValidationException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

//@ActiveProfiles("test")
@SpringBootTest
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProjectServiceTest {

    @Autowired
    private ProjectRepository repository;

    @Autowired
    private ProjectService service;

    @BeforeEach
    void setup() {
        repository.deleteAll();
    }

    ProjectDTO createProjectDTO() {
        return new ProjectDTO(null, "Projeto X", "PRJTX");
    }

    @Test
    @DisplayName("Deve cadastrar um novo projeto com sucesso")
    void Case1() {
        ProjectDTO projectDTO = createProjectDTO();

        ProjectDTO result = service.insert(projectDTO);

        assertNotNull(result.getId());
        assertEquals("Projeto X", result.getName());
        assertEquals("PRJTX", result.getIdentifier());

        Optional<Project> databaseObject = repository.findById(result.getId());
        assertTrue(databaseObject.isPresent());
    }

    @Test
    @DisplayName("Não deve cadastrar um projeto quando o nome for nulo")
    void Case2() {
        ProjectDTO projectDTO = createProjectDTO();
        projectDTO.setName(null);

        ValidationException exception = assertThrows(
                ValidationException.class,
                () -> service.insert(projectDTO)
        );

        Map<String, String> errors = exception.getErrors();

        assertTrue(errors.containsKey("name"));
        assertEquals("O nome é obrigatório.", errors.get("name"));
    }

    @Test
    @DisplayName("Não deve cadastrar um projeto quando o nome for vazio")
    void Case3() {
        ProjectDTO projectDTO = createProjectDTO();
        projectDTO.setName("");

        ValidationException exception = assertThrows(
                ValidationException.class,
                () -> service.insert(projectDTO)
        );

        Map<String, String> errors = exception.getErrors();

        assertTrue(errors.containsKey("name"));
        assertEquals("O nome é obrigatório.", errors.get("name"));
    }

    @Test
    @DisplayName("Não deve cadastrar um projeto quando o nome for inválido")
    void Case4() {
        ProjectDTO projectDTO = createProjectDTO();
        projectDTO.setName("  ");
        System.out.print(projectDTO.getName());

        ValidationException exception = assertThrows(
                ValidationException.class,
                () -> service.insert(projectDTO)
        );

        Map<String, String> errors = exception.getErrors();

        assertTrue(errors.containsKey("name"));
        assertEquals("O nome é obrigatório.", errors.get("name"));
    }

    @Test
    @DisplayName("Não deve cadastrar um projeto quando o nome for menor que 3 caracteres")
    void Case5() {
        ProjectDTO projectDTO = createProjectDTO();
        projectDTO.setName("AB");

        ValidationException exception = assertThrows(
                ValidationException.class,
                () -> service.insert(projectDTO)
        );

        Map<String, String> errors = exception.getErrors();

        assertTrue(errors.containsKey("name"));
        assertEquals("O nome deve possuir entre 3 e 50 caracteres.", errors.get("name"));
    }

    @Test
    @DisplayName("Não deve cadastrar um projeto quando o nome for maior que 50 caracteres")
    void Case6() {
        ProjectDTO projectDTO = createProjectDTO();
        projectDTO.setName("Lorem ipsum dolor sit amet, consectetur adipiscinge");

        ValidationException exception = assertThrows(
                ValidationException.class,
                () -> service.insert(projectDTO)
        );

        Map<String, String> errors = exception.getErrors();

        assertTrue(errors.containsKey("name"));
        assertEquals("O nome deve possuir entre 3 e 50 caracteres.", errors.get("name"));
    }

    @Test
    @DisplayName("Não deve cadastrar um projeto quando o identificador for nulo")
    void Case7() {
        ProjectDTO projectDTO = createProjectDTO();
        projectDTO.setIdentifier(null);

        ValidationException exception = assertThrows(
                ValidationException.class,
                () -> service.insert(projectDTO)
        );

        Map<String, String> errors = exception.getErrors();

        assertTrue(errors.containsKey("identifier"));
        assertEquals("O identificador é obrigatório.", errors.get("identifier"));
    }

    @Test
    @DisplayName("Não deve cadastrar um projeto quando o identificador for vazio")
    void Case8() {
        ProjectDTO projectDTO = createProjectDTO();
        projectDTO.setIdentifier("");

        ValidationException exception = assertThrows(
                ValidationException.class,
                () -> service.insert(projectDTO)
        );

        Map<String, String> errors = exception.getErrors();

        assertTrue(errors.containsKey("identifier"));
        assertEquals("O identificador é obrigatório.", errors.get("identifier"));
    }

    @Test
    @DisplayName("Não deve cadastrar um projeto quando o identificador for inválido")
    void Case9() {
        ProjectDTO projectDTO = createProjectDTO();
        projectDTO.setIdentifier("XA9");

        ValidationException exception = assertThrows(
                ValidationException.class,
                () -> service.insert(projectDTO)
        );

        Map<String, String> errors = exception.getErrors();

        assertTrue(errors.containsKey("identifier"));
        assertEquals("O identificador deve possuir apenas caracteres de A-Z.", errors.get("identifier"));
    }

    @Test
    @DisplayName("Não deve cadastrar um projeto quando o identificador for menor que 3 caracteres")
    void Case10() {
        ProjectDTO projectDTO = createProjectDTO();
        projectDTO.setIdentifier("AB");

        ValidationException exception = assertThrows(
                ValidationException.class,
                () -> service.insert(projectDTO)
        );

        Map<String, String> errors = exception.getErrors();

        assertTrue(errors.containsKey("identifier"));
        assertEquals("O identificador deve possuir entre 3 e 5 caracteres.", errors.get("identifier"));
    }

    @Test
    @DisplayName("Não deve cadastrar um projeto quando o identificador for maior que 5 caracteres")
    void Case11() {
        ProjectDTO projectDTO = createProjectDTO();
        projectDTO.setIdentifier("PROJCT");

        ValidationException exception = assertThrows(
                ValidationException.class,
                () -> service.insert(projectDTO)
        );

        Map<String, String> errors = exception.getErrors();

        assertTrue(errors.containsKey("identifier"));
        assertEquals("O identificador deve possuir entre 3 e 5 caracteres.", errors.get("identifier"));
    }



    @Test
    @DisplayName("Não deve cadastrar um projeto quando o identificador já estiver cadastrado")
    void Case12() {
        ProjectDTO projectDTO = createProjectDTO();
        service.insert(projectDTO);

        ValidationException exception = assertThrows(
                ValidationException.class,
                () -> service.insert(projectDTO)
        );

        Map<String, String> errors = exception.getErrors();

        assertTrue(errors.containsKey("identifier"));
        assertEquals("O identificador 'PRJTX' já está sendo utilizado.", errors.get("identifier"));

    }

    @Test
    @DisplayName("Deve editar um projeto com sucesso")
    void Case13() {
        ProjectDTO projectDTO = createProjectDTO();

        ProjectDTO result = service.insert(projectDTO);
        result.setName("Projeto Y");
        result = service.update(result.getId(), result);

        assertEquals("Projeto Y", result.getName());
        assertEquals("PRJTX", result.getIdentifier());

        Optional<Project> databaseObject = repository.findById(result.getId());
        assertTrue(databaseObject.isPresent());
    }





}
