package co.edu.uniquindio.proyecto.interfaceService;

import co.edu.uniquindio.proyecto.entidades.Mensaje;

import java.util.List;
import java.util.Optional;

public interface IMensajeService {


    List<Mensaje> listar();


    Optional<Mensaje> buscarPorId(int c);
}
