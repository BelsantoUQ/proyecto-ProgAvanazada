package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.entidades.Subasta_Usuario;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.interfaceService.ISubastaUsuarioService;
import co.edu.uniquindio.proyecto.repositorios.Subasta_UsuarioRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubastaUsuarioService implements ISubastaUsuarioService {

    @Autowired
    private Subasta_UsuarioRepo data;

    @Autowired
    private UsuarioRepo userRepo;

    @Override
    public Subasta_Usuario guardar(Subasta_Usuario s) throws Exception {
        if (data.findById(s.getCodigo()).isEmpty()){
            return data.save(s);

        }else throw new Exception("Ya se existe una subasta con este codigo en la BD");
    }

    @Override
    public Subasta_Usuario actualizar(Subasta_Usuario s) throws Exception {
        if (!data.findById(s.getCodigo()).isEmpty()){
            return data.save(s);

        }else throw new Exception("No se encuentra una subasta con este codigo en la BD");
    }

    @Override
    public void eliminar(int codigo) throws Exception {

        if (!data.findById(codigo).isEmpty()){
            data.deleteById(codigo);

        }else throw new Exception("No se encuentra una subasta con este codigo en la BD");

    }

    @Override
    public Subasta_Usuario obtenerSubastaUsuario(int codigo) throws Exception {
        return data.findById(codigo).orElseThrow(() -> new Exception("No se encontro un dato con este codigo"));
    }

    @Override
    public List<Subasta_Usuario> listar() {
        return data.findAll();
    }

    @Override
    public List<Subasta_Usuario> listarPorUsuario(Usuario user) throws Exception {
        if (!userRepo.findById(user.getCodigo()).isEmpty()) {
            return data.findAllByUsuarioSubasta(user);
        }else throw new Exception("No se encontro el vendedor");
    }

}
