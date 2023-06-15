package com.example.colegioelite.validadores;

import com.example.colegioelite.entidades.Estudiante;

import java.util.Optional;

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
