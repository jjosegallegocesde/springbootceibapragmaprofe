package com.example.colegioelite.entidades;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EstudianteTest {

    @Mock
    private Acudiente acudienteMock;
    private Estudiante estudiante;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        estudiante = new Estudiante(1, "Juan", "123456789", "juan@correo.com", acudienteMock);
    }

    @Test
    public void probarGetters() {
        // Verificar los valores iniciales
        Assertions.assertEquals(1, estudiante.getId());
        Assertions.assertEquals("Juan", estudiante.getNombre());
        Assertions.assertEquals("123456789", estudiante.getDocumento());
        Assertions.assertEquals("juan@correo.com", estudiante.getCorreo());
        Assertions.assertEquals(acudienteMock, estudiante.getAcudiente());

    }
    @Test
    public void probarSetters() {
        // Modificar los valores y verificar los cambios
        estudiante.setId(2);
        estudiante.setNombre("Pedro");
        estudiante.setDocumento("987654321");
        estudiante.setCorreo("pedro@example.com");

        Assertions.assertEquals(2, estudiante.getId());
        Assertions.assertEquals("Pedro", estudiante.getNombre());
        Assertions.assertEquals("987654321", estudiante.getDocumento());
        Assertions.assertEquals("pedro@example.com", estudiante.getCorreo());



    }


}