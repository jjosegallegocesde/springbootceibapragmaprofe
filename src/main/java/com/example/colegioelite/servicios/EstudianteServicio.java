package com.example.colegioelite.servicios;

import com.example.colegioelite.DTO.EstudianteDTO;
import com.example.colegioelite.entidades.Acudiente;
import com.example.colegioelite.entidades.Estudiante;
import com.example.colegioelite.entidades.Materia;
import com.example.colegioelite.mapas.EstudianteMapa;
import com.example.colegioelite.repositorios.EstudianteRepositorio;
import com.example.colegioelite.validadores.EstudianteValidaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EstudianteServicio implements ServicioBaseDTO<Estudiante,EstudianteDTO>  {

    @Autowired
    private EstudianteRepositorio estudianteRepositorio;
    @Autowired
    private EstudianteMapa estudianteMapa;

    @Autowired
    private EstudianteValidaciones validaciones;

    @Override
    public List<EstudianteDTO> buscarTodos() throws Exception {
        try{
            List<EstudianteDTO>estudiantes=estudianteMapa.mapearEstudiantes(estudianteRepositorio.findAll());
            return estudiantes;

        }catch(Exception error){
            throw new Exception(error.getMessage());

        }
    }

    @Override
    public EstudianteDTO buscarPorId(Integer id) throws Exception {
        try{
          Optional<Estudiante>estudianteOpcional=estudianteRepositorio.findById(id);
            if(estudianteOpcional.isPresent()){
                return  estudianteMapa.mapearEstudiante(estudianteOpcional.get());
            }else{
                throw new Exception("Usuario no encontrado");
            }
        }catch(Exception error){
            throw new Exception(error.getMessage());
        }
    }

    @Override
    public EstudianteDTO registrar(Estudiante datosARegistrar) throws Exception {
        try{
            if(validaciones.verificarCorreo(datosARegistrar.getCorreo())){
                throw new Exception("revise el email por favor");
            }else{
                EstudianteDTO estudianteGuardado= estudianteMapa.mapearEstudiante(estudianteRepositorio.save(datosARegistrar));
                return estudianteGuardado;
            }
        }catch(Exception error){
            throw new Exception(error.getMessage());
        }
    }

    @Override
    public EstudianteDTO actualizar(Integer id, Estudiante datosNuevos) throws Exception {
        return null;
    }

    @Override
    public boolean eliminar(Integer id) throws Exception {
        return false;
    }
}
