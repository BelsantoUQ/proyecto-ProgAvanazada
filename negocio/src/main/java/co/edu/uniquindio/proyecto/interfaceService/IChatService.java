package co.edu.uniquindio.proyecto.interfaceService;


import co.edu.uniquindio.proyecto.entidades.Chat;

import java.util.List;
import java.util.Optional;

public interface IChatService {


    List<Chat> listar();


    Optional<Chat> buscarPorId(int c);


}
