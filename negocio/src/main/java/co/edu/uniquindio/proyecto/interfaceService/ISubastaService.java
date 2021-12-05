package co.edu.uniquindio.proyecto.interfaceService;


import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.entidades.Subasta_Usuario;
import co.edu.uniquindio.proyecto.entidades.Usuario;

import java.util.List;
import java.util.Optional;

public interface ISubastaService {

    Subasta guardar (Subasta s) throws Exception;

    Subasta actualizar (Subasta s) throws Exception;

    void eliminar(int codigo) throws Exception;

    Subasta obtenerSubasta(int codigo) throws Exception;

    List<Subasta> listar();

    List<Subasta> listarPorVendedor(Usuario u) throws Exception;

    List<Subasta> listarPorCategoria(Categoria c);

    Optional<Usuario> obtenerUsuarioMejor(int codigo) throws Exception;

    Subasta_Usuario obtenerDetalleSubastaPorUser(Usuario u) throws Exception;

    void ofertar(Subasta s, Usuario u, float valor) throws Exception;

    float obtenerMejorOferta(int codigo);

}
