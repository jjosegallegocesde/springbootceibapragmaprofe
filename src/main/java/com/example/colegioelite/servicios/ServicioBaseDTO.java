package com.example.colegioelite.servicios;

import java.util.List;

public interface ServicioBaseDTO<E,DTO> {

    List<DTO> buscarTodos() throws Exception;
    DTO buscarPorId(Integer id) throws  Exception;
    DTO registrar(E datosARegistrar) throws Exception;
    DTO actualizar(Integer id, E datosNuevos) throws Exception;
    boolean eliminar(Integer id) throws Exception;
}
