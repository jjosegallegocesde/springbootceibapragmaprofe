package com.example.colegioelite.repositorios;

import com.example.colegioelite.entidades.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MateriaRepositorio extends JpaRepository<Materia,Integer> {
}
