package com.example.colegioelite.controladores;

import com.example.colegioelite.DTO.ErrorDTO;
import com.example.colegioelite.DTO.EstudianteDTO;
import com.example.colegioelite.entidades.Estudiante;
import com.example.colegioelite.servicios.EstudianteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/estudiantes")

public class ControladorEstudiante {

    @Autowired
    protected EstudianteServicio estudianteServicio;

    @PostMapping
    public ResponseEntity<EstudianteDTO> registrar(@RequestBody Estudiante datosEstudiante){
        try{
            EstudianteDTO estudianteRegistrado= estudianteServicio.registrar(datosEstudiante);
            return new ResponseEntity<>(estudianteRegistrado,HttpStatus.CREATED);
        }catch(Exception error){
            ErrorDTO errorDTO= new ErrorDTO();
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<EstudianteDTO>> buscarTodos(){
        try{
            List<EstudianteDTO> estudiantes = estudianteServicio.buscarTodos();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(estudiantes);
        }catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }


}
