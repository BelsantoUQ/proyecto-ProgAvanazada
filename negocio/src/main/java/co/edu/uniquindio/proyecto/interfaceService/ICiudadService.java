package co.edu.uniquindio.proyecto.interfaceService;

import co.edu.uniquindio.proyecto.entidades.Ciudad;

import java.util.List;
import java.util.Optional;

public interface ICiudadService {

    Ciudad guardar (Ciudad c) throws Exception;

    void eliminar(int codigo) throws Exception;

    Ciudad obtenerCiudad(int codigo) throws Exception;

    List<Ciudad> listar();

}
