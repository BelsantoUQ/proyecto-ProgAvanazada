package co.edu.uniquindio.proyecto.interfaceService;

import co.edu.uniquindio.proyecto.entidades.Subasta_Usuario;
import co.edu.uniquindio.proyecto.entidades.Usuario;

import java.util.List;

public interface ISubastaUsuarioService {

    Subasta_Usuario guardar (Subasta_Usuario s) throws Exception;

    Subasta_Usuario actualizar (Subasta_Usuario s) throws Exception;

    void eliminar(int codigo) throws Exception;

    Subasta_Usuario obtenerSubastaUsuario(int codigo) throws Exception;

    List<Subasta_Usuario> listar();

    List<Subasta_Usuario> listarPorUsuario(Usuario u) throws Exception;
}
