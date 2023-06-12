package com.example.colegioelite.servicios;

import com.example.colegioelite.DTO.EstudianteDTO;
import com.example.colegioelite.entidades.Materia;
import com.example.colegioelite.entidades.Profesor;
import com.example.colegioelite.repositorios.MateriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class MateriaServicio implements ServicioBase<Materia>{

    @Autowired
    protected MateriaRepositorio materiaRepositorio;

    @Override
    public List<Materia> buscarTodos() throws Exception {
        try{
            List<Materia>materias=materiaRepositorio.findAll();
            return materias;
        }catch(Exception error){
            throw new Exception(error.getMessage());
        }
    }

    @Override
    public Materia buscarPorId(Integer id) throws Exception {
        try{
            Optional<Materia> materiaOpcional =materiaRepositorio.findById(id);
            if(materiaOpcional.isPresent()){
                return materiaOpcional.get();
            }else{
                throw new Exception("Usuario no encontrado");
            }
        }catch(Exception error){
            throw new Exception(error.getMessage());
        }
    }

    @Override
    public Materia registrar(Materia datosARegistrar) throws Exception {
        try{
            Materia materiaGuardado=materiaRepositorio.save(datosARegistrar);
            return materiaGuardado;
        }catch(Exception error){
            throw new Exception(error.getMessage());
        }
    }

    @Override
    public Materia actualizar(Integer id, Materia datosNuevos) throws Exception {
        try{
            Optional<Materia>materiaOpcional =materiaRepositorio.findById(id);
            if(materiaOpcional.isPresent()){
                Materia materiaActualizado=materiaRepositorio.save(datosNuevos);
                return materiaActualizado;
            }else{
                throw new Exception("Usuario no encontrado");
            }
        }catch(Exception error){
            throw new Exception(error.getMessage());
        }
    }

    @Override
    public boolean eliminar(Integer id) throws Exception {
        try{

            Optional<Materia>materiaOpcional =materiaRepositorio.findById(id);
            if(materiaOpcional.isPresent()){
                materiaRepositorio.deleteById(id);
                return true;
            }else{
                throw new Exception("Usuario no encontrado");
            }

        }catch(Exception error){
            throw new Exception(error.getMessage());
        }
    }
}
