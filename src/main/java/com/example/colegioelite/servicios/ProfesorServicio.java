package com.example.colegioelite.servicios;

import com.example.colegioelite.entidades.Acudiente;
import com.example.colegioelite.entidades.Profesor;
import com.example.colegioelite.repositorios.ProfesorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ProfesorServicio implements ServicioBase<Profesor> {

    @Autowired
    protected ProfesorRepositorio profesorRepositorio;

    @Override
    public List<Profesor> buscarTodos() throws Exception {
        try{
            List<Profesor>profesores=profesorRepositorio.findAll();
            return profesores;
        }catch(Exception error){
            throw new Exception(error.getMessage());
        }
    }

    @Override
    public Profesor buscarPorId(Integer id) throws Exception {
        try{
            Optional<Profesor> profesorOpcional =profesorRepositorio.findById(id);
            if(profesorOpcional.isPresent()){
                return profesorOpcional.get();
            }else{
                throw new Exception("Usuario no encontrado");
            }
        }catch(Exception error){
            throw new Exception(error.getMessage());
        }
    }

    @Override
    public Profesor registrar(Profesor datosARegistrar) throws Exception {
        try{
            Profesor profesorGuardado=profesorRepositorio.save(datosARegistrar);
            return profesorGuardado;
        }catch(Exception error){
            throw new Exception(error.getMessage());
        }
    }

    @Override
    public Profesor actualizar(Integer id, Profesor datosNuevos) throws Exception {
        try{
            Optional<Profesor>profesorOpcional =profesorRepositorio.findById(id);
            if(profesorOpcional.isPresent()){
                Profesor profesorActualizado=profesorRepositorio.save(datosNuevos);
                return profesorActualizado;
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

            Optional<Profesor>profesorOpcional =profesorRepositorio.findById(id);
            if(profesorOpcional.isPresent()){
                profesorRepositorio.deleteById(id);
                return true;
            }else{
                throw new Exception("Usuario no encontrado");
            }

        }catch(Exception error){
            throw new Exception(error.getMessage());
        }
    }
}
