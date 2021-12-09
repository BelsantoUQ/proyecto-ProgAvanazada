package co.edu.uniquindio.proyecto.rest;

import co.edu.uniquindio.proyecto.dto.Mensaje;
import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.entidades.Chat;
import co.edu.uniquindio.proyecto.interfaceService.IAdminService;
import co.edu.uniquindio.proyecto.interfaceService.IChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/chats")
public class ChatRestController {

    @Autowired
    private IChatService data;

    @GetMapping
    public ResponseEntity<?> listar(){
        List<Chat> list= data.listar();
        return ResponseEntity.status(200).body(list);
    }

    @GetMapping("obtener/{id}")
    public ResponseEntity<?> obtener(@PathVariable("id") String id){
        try {
            Chat d = data.buscarPorId(Integer.parseInt(id)).get();
            return ResponseEntity.status(200).body(d);
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

}
