package co.edu.uniquindio.proyecto.servicios;


import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.entidades.Subasta_Usuario;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.interfaceService.ISubastaService;
import co.edu.uniquindio.proyecto.repositorios.SubastaRepo;
import co.edu.uniquindio.proyecto.repositorios.Subasta_UsuarioRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubastaService implements ISubastaService {

    @Autowired
    private SubastaRepo data;

    @Autowired
    private UsuarioRepo userRepo;

    @Autowired
    private Subasta_UsuarioRepo subastaUsuarioRepo;

    @Override
    public Subasta guardar(Subasta s) throws Exception {
        if (data.findById(s.getCodigo()).isEmpty()){
            return data.save(s);

        }else throw new Exception("Ya se existe una subasta con este codigo en la BD");
    }

    @Override
    public Subasta actualizar(Subasta s) throws Exception {
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
    public Subasta obtenerSubasta(int codigo) throws Exception {
        return data.findById(codigo).orElseThrow(() -> new Exception("No se encontro un dato con este codigo"));
    }

    @Override
    public List<Subasta> listar() {
        return data.listarSubastas();
    }

    @Override
    public List<Subasta> listarPorVendedor(Usuario user) throws Exception {
        if (!userRepo.findById(user.getCodigo()).isEmpty()) {
            return data.listarSubastasVendedor(user);
        }else throw new Exception("No se encontro el vendedor");
    }

    @Override
    public List<Subasta> listarPorCategoria(Categoria c){
        return data.listarSubastasPorCategoria(c);
    }

    @Override
    public Optional<Usuario> obtenerUsuarioMejor(int codigo) throws Exception {
        if (!data.findById(codigo).isEmpty()){
            return subastaUsuarioRepo.obtenerUsuarioGanador(codigo);
        }else throw new Exception("No se encontro la subasta");
    }

    @Override
    public Subasta_Usuario obtenerDetalleSubastaPorUser(Usuario u) throws Exception {
        return subastaUsuarioRepo.findSubasta_UsuarioByUsuarioSubasta(u).orElseThrow(()
                -> new Exception("No se encontro una ciudad registrada con este codigo"));
    }
}
