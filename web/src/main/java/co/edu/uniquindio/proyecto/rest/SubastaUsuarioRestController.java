package co.edu.uniquindio.proyecto.rest;

import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.entidades.Subasta_Usuario;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.interfaceService.ISubastaService;
import co.edu.uniquindio.proyecto.interfaceService.ISubastaUsuarioService;
import co.edu.uniquindio.proyecto.interfaceService.IUsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/usuario")
public class SubastaUsuarioRestController {

    @Autowired
    private IUsuarioServicio data;

    @GetMapping
    public ResponseEntity<?> listar(){
        List<Usuario> list= data.listar();
        return ResponseEntity.status(200).body(list);
    }

    @GetMapping("obtener/{id}")
    public ResponseEntity<?> obtener(@PathVariable("id") String id){
        try {
            Usuario d = data.obtenerUsuario(Integer.parseInt(id)).get();
            return ResponseEntity.status(200).body(d);
        }catch (Exception e){
            return ResponseEntity.status(500).body(new co.edu.uniquindio.proyecto.dto.Mensaje(e.getMessage()));
        }
    }
}
