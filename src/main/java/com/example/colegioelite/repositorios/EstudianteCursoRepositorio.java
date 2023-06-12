package com.example.colegioelite.repositorios;

import com.example.colegioelite.entidades.EstudianteCurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteCursoRepositorio extends JpaRepository<EstudianteCurso,Integer> {
}
