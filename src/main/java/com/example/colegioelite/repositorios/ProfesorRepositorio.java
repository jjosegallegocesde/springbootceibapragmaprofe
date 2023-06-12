package com.example.colegioelite.repositorios;

import com.example.colegioelite.entidades.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorRepositorio extends JpaRepository<Profesor,Integer> {
}
