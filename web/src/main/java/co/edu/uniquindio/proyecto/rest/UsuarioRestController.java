package co.edu.uniquindio.proyecto.rest;

import co.edu.uniquindio.proyecto.entidades.Subasta_Usuario;
import co.edu.uniquindio.proyecto.interfaceService.ISubastaUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public class UsuarioRestController {

    @Autowired
    private ISubastaUsuarioService data;

    @GetMapping
    public ResponseEntity<?> listar(){
        List<Subasta_Usuario> list= data.listar();
        return ResponseEntity.status(200).body(list);
    }

    @GetMapping("obtener/{id}")
    public ResponseEntity<?> obtener(@PathVariable("id") String id){
        try {
            Subasta_Usuario d = data.obtenerSubastaUsuario(Integer.parseInt(id));
            return ResponseEntity.status(200).body(d);
        }catch (Exception e){
            return ResponseEntity.status(500).body(new co.edu.uniquindio.proyecto.dto.Mensaje(e.getMessage()));
        }
    }
}
