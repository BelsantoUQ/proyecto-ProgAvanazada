package co.edu.uniquindio.proyecto.rest;

import co.edu.uniquindio.proyecto.dto.MensajeDto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class MessageController {


    private final ThreadLocal<SimpMessagingTemplate> simpMessagingTemplate = new ThreadLocal<SimpMessagingTemplate>();

    @Autowired
    private UsuarioService usuarioServicio;

    public void sendMessage(@DestinationVariable String to, MensajeDto message) {
        System.out.println("handling send message: " + message + " to: " + to);
        Optional<Usuario> user = null;
        try {
            user = usuarioServicio.obtenerUsuario(Integer.parseInt(to));
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean isExists = (!user.isEmpty());
        if (isExists) {
            simpMessagingTemplate.get().convertAndSend("/topic/messages/" + user.get().getUsername(), message);
        }
    }
}
