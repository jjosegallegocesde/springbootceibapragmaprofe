package com.example.colegioelite.controladores;

import com.example.colegioelite.DTO.EstudianteDTO;
import com.example.colegioelite.entidades.Estudiante;
import com.example.colegioelite.servicios.EstudianteServicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ControladorEstudianteTest {

    @Mock
    private EstudianteServicio estudianteServicio;

    @InjectMocks
    private ControladorEstudiante controladorEstudiante;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registrarDebeRetornarEstudianteRegistrado() throws Exception {
        // Arrange
        EstudianteDTO estudianteRegistrado = new EstudianteDTO();
        when(estudianteServicio.registrar(any(Estudiante.class))).thenReturn(estudianteRegistrado);

        // Act
        ResponseEntity<EstudianteDTO> respuesta = controladorEstudiante.registrar(new Estudiante());

        // Assert
        assertEquals(estudianteRegistrado, respuesta.getBody());
        assertEquals(HttpStatus.CREATED, respuesta.getStatusCode());
    }

    @Test
    void registrarAlOcurrirErrorDebeRetornarBadRequest() throws Exception {
        // Arrange
        String errorMessage = "Error al registrar estudiante.";
        when(estudianteServicio.registrar(any(Estudiante.class))).thenThrow(new Exception(errorMessage));

        // Act
        ResponseEntity<EstudianteDTO> response = controladorEstudiante.registrar(new Estudiante());

        // Assert
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void buscarTodosDebeRetornarListaEstudiantes() throws Exception {
        // Arrange
        List<EstudianteDTO> estudiantes = new ArrayList<>();
        when(estudianteServicio.buscarTodos()).thenReturn(estudiantes);

        // Act
        ResponseEntity<List<EstudianteDTO>> response = controladorEstudiante.buscarTodos();

        // Assert
        assertEquals(estudiantes, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void buscarTodosAlOcurrirErrorDebeRetornarNotFound() throws Exception {
        // Arrange
        when(estudianteServicio.buscarTodos()).thenThrow(new Exception());

        // Act
        ResponseEntity<List<EstudianteDTO>> response = controladorEstudiante.buscarTodos();

        // Assert
        assertNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }



}