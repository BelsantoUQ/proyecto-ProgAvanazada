package co.edu.uniquindio.proyecto.servicios;


import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.interfaceService.IAdminService;
import co.edu.uniquindio.proyecto.repositorios.AdministradorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService implements IAdminService {

    @Autowired
    private AdministradorRepo data;

    @Autowired
    private SendMailService mailSenderService;

    @Override
    public List<Administrador> listar() {
        return data.findAll();
    }

    @Override
    public Optional<Administrador> buscarPorEmail(String email) {
        return data.findByEmail(email);
    }

    @Override
    public Optional<Administrador> buscarPorId(int c) {
        return data.findById(c);
    }

    @Override
    public Optional<Administrador> iniciarSesion(String email, String contraseña) {
        return data.findByEmailAndPassword(email,contraseña);
    }

    @Override
    public void recuperarContraseña(Administrador usuario) {
        mailSenderService.sendMail("elitesantymetal@gmail.com",usuario.getEmail(),"RECUPERACION DE CONTRASEÑA"
                ,"Buenos dias, tardes o noches, este mensaje es enviado para indicar que ha intentado ingresar " +
                        "3 veces sin exito así que aqui está su contraseña"+usuario.getPassword());
    }
}
