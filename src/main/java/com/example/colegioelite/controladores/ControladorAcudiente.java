package com.example.colegioelite.controladores;

import com.example.colegioelite.entidades.Acudiente;
import com.example.colegioelite.entidades.Estudiante;
import com.example.colegioelite.servicios.AcudienteServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/acudientes")
@Tag(name = "Acudientes", description = "Endpoints relacionados con los acudientes")
public class ControladorAcudiente {

    @Autowired
    protected AcudienteServicio acudienteServicio;

    @PostMapping
    @Operation(summary = "Registrar un acudiente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Acudiente registrado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error al registrar el acudiente")
    })
    public ResponseEntity<Acudiente> registrar(@RequestBody Acudiente datosAcudiente){
        try{
            Acudiente acudienteRegistrado= acudienteServicio.registrar(datosAcudiente);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(acudienteRegistrado);
        }catch(Exception error){
            String errorMensaje="Tenemos un error: "+error.getMessage();
            Acudiente acudienteError= new Acudiente();
            acudienteError.setErrorMensaje(errorMensaje);
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(acudienteError);
        }
    }

    @GetMapping
    public ResponseEntity<List<Acudiente>> buscarTodos(){
        try{
            List<Acudiente> acudientes = acudienteServicio.buscarTodos();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(acudientes);
        }catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }



}
