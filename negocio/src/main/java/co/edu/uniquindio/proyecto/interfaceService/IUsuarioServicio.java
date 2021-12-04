package co.edu.uniquindio.proyecto.interfaceService;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioServicio {


    Usuario guardar(Usuario u) throws Exception;

    Usuario actualizar(Usuario u) throws Exception;

    int validarExistente(Usuario u); //1 codigo ya existe, 2 email ya existe, 3 username ya existe.

    void eliminar(int codigo) throws Exception;

    List<Usuario> listar();

    List<Producto> listarProductosFavoritos(String email) throws Exception;

    Optional<Usuario> buscarPorEmail(String email);

    Optional<Usuario> obtenerUsuario(int codigo) throws Exception;

    Optional<Usuario> iniciarSesion(String email, String contraseña);

    void recuperarContraseña(Usuario usuario);

}
