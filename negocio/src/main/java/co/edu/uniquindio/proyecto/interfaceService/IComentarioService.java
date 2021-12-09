package co.edu.uniquindio.proyecto.interfaceService;

import co.edu.uniquindio.proyecto.entidades.Chat;
import co.edu.uniquindio.proyecto.entidades.Comentario;

import java.util.List;
import java.util.Optional;

public interface IComentarioService {



    List<Comentario> listar();


    Optional<Comentario> buscarPorId(int c);

}
