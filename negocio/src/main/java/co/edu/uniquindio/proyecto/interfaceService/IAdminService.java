package co.edu.uniquindio.proyecto.interfaceService;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.entidades.Usuario;

import java.util.List;
import java.util.Optional;

public interface IAdminService {

    List<Administrador> listar();

    Optional<Administrador> buscarPorEmail(String email);

    Optional<Administrador> buscarPorId(int c);

    Optional<Administrador> iniciarSesion(String email, String contraseña);

    void recuperarContraseña(Administrador a);

}
