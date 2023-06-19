package com.example.colegioelite.servicios;

import com.example.colegioelite.DTO.EstudianteDTO;
import com.example.colegioelite.entidades.Estudiante;
import com.example.colegioelite.mapas.EstudianteMapa;
import com.example.colegioelite.repositorios.EstudianteRepositorio;
import com.example.colegioelite.validadores.EstudianteValidaciones;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class EstudianteServicioTest {

    private Estudiante estudiante;
    private EstudianteDTO estudianteDTO;

    @Mock
    private EstudianteRepositorio estudianteRepositorioMock;

    @Mock
    private EstudianteMapa estudianteMapaMock;

    @Mock
    private EstudianteValidaciones validacionesMock;


    @InjectMocks //Inyectamos los mock en el servicio
    private EstudianteServicio estudianteServicio;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        estudiante = new Estudiante(1, "Juan", "123456789", "juan@correo.com", null);
    }

    @Test
    public void buscarTodosDebeRetornarListaEstudiantes() throws Exception {

        //creamos una lista de estudiantes al ejecutar el repo
        List<Estudiante> estudiantes = new ArrayList<>();
        estudiantes.add(estudiante);
        Mockito.when(estudianteRepositorioMock.findAll()).thenReturn(estudiantes);
        //el mapeo debe crear una una lista de estudiantes DTO
        List<EstudianteDTO> listaEstudiantesDTO = new ArrayList<>();
        estudianteDTO = new EstudianteDTO();
        estudianteDTO.setNombre("Juan");
        estudianteDTO.setCorreo("juan@correo.com");
        listaEstudiantesDTO.add(estudianteDTO);
        //al mapear la lista que me envia el repo, debo tener una lista de estudiantesDTO
        Mockito.when(estudianteMapaMock.mapearEstudiantes(estudiantes)).thenReturn(listaEstudiantesDTO);
        //llamando el metodo a probar
        List<EstudianteDTO> resultado = estudianteServicio.buscarTodos();
        assertEquals(listaEstudiantesDTO, resultado);
    }

    @Test
    public void buscarTodosDebeRetornarListaEstudiantesExcepcion() throws Exception{
        Exception excepcionSimulada = new RuntimeException("Error al buscar estudiantes");
        // Configuramos el comportamiento del repositorio mock para lanzar la excepción
        Mockito.when(estudianteRepositorioMock.findAll()).thenThrow(excepcionSimulada);
        // Llamada al método a probar
        try {
            estudianteServicio.buscarTodos();
            fail("Se esperaba una excepción"); // Aseguramos que se lance una excepción
        } catch (Exception e) {
            // Verificamos que la excepción lanzada sea la esperada
            assertEquals("Error al buscar estudiantes", e.getMessage());
        }
    }

    @Test
    public void registrarConDatosValidosDebeRetornarEstudianteDTO() throws Exception {
        // Datos de prueba
        EstudianteDTO estudianteDTO = new EstudianteDTO();
        when(validacionesMock.verificarCorreo(estudiante.getCorreo())).thenReturn(false);
        when(estudianteRepositorioMock.save(estudiante)).thenReturn(estudiante);
        when(estudianteMapaMock.mapearEstudiante(estudiante)).thenReturn(estudianteDTO);
        // Prueba del método
        EstudianteDTO resultado = estudianteServicio.registrar(estudiante);
        // Verificación de resultados
        assertEquals(estudianteDTO, resultado);
    }

    @Test
    public void registrar_ConCorreoInvalido_DebeLanzarExcepcion() throws Exception {
        // Datos de prueba
        Estudiante estudiante = new Estudiante();
        when(validacionesMock.verificarCorreo(estudiante.getCorreo())).thenReturn(true);
        // Prueba del método y verificación de la excepción
        assertThrows(Exception.class, () -> estudianteServicio.registrar(estudiante));
    }

    @Test
    public void buscarPorIdExistenteDebeRetornarEstudianteDTO() throws Exception {
        int id=1;
        EstudianteDTO estudianteDTO = new EstudianteDTO();
        Mockito.when(estudianteRepositorioMock.findById(id)).thenReturn(Optional.of(estudiante));
        Mockito.when(estudianteMapaMock.mapearEstudiante(estudiante)).thenReturn(estudianteDTO);
        EstudianteDTO resultado = estudianteServicio.buscarPorId(id);
        assertEquals(estudianteDTO, resultado);
    }

    @Test
    public void buscarPorIdNoExistenteDebeLanzarExcepcion() {
        int id = 1;
        Mockito.when(estudianteRepositorioMock.findById(id)).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> estudianteServicio.buscarPorId(id));
    }





}