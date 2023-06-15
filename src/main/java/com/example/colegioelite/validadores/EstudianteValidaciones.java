package com.example.colegioelite.validadores;

import com.example.colegioelite.entidades.Estudiante;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EstudianteValidaciones {

    public boolean verificarExisteEstudiante(Optional<Estudiante> estudiante){
        return estudiante.isPresent();
    }

    public boolean verificarCorreo(String email){
        if (email.isEmpty()) {
            return true;
        }else{
            return false;
        }
    }

}
