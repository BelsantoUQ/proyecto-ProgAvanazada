package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.interfaceService.IComentarioService;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComentarioService implements IComentarioService {

    @Autowired
    private ComentarioRepo data;

    @Override
    public List<Comentario> listar() {
        return data.findAll();
    }

    @Override
    public Optional<Comentario> buscarPorId(int c) {
        return data.findById(c);
    }
}
