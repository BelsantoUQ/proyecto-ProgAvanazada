package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Mensaje;
import co.edu.uniquindio.proyecto.interfaceService.IMensajeService;
import co.edu.uniquindio.proyecto.repositorios.MensajeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MensajeService implements IMensajeService {

    @Autowired
    private MensajeRepo data;

    @Override
    public List<Mensaje> listar() {
        return data.findAll();
    }

    @Override
    public Optional<Mensaje> buscarPorId(int c) {
        return data.findById(c);
    }
}
