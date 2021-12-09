package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Chat;
import co.edu.uniquindio.proyecto.interfaceService.IChatService;
import co.edu.uniquindio.proyecto.repositorios.ChatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatService implements IChatService {

    @Autowired
    private ChatRepo data;

    @Override
    public List<Chat> listar() {
        return data.findAll();
    }

    @Override
    public Optional<Chat> buscarPorId(int c) {
        return data.findById(c);
    }
}
