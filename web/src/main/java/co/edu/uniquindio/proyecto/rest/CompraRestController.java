package co.edu.uniquindio.proyecto.rest;

import co.edu.uniquindio.proyecto.dto.Mensaje;
import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.interfaceService.IComentarioService;
import co.edu.uniquindio.proyecto.interfaceService.ICompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/compras")
public class CompraRestController {

    @Autowired
    private ICompraService data;

    @GetMapping
    public ResponseEntity<?> listar(){
        List<Compra> list= data.listar();
        return ResponseEntity.status(200).body(list);
    }

    @GetMapping("obtener/{id}")
    public ResponseEntity<?> obtener(@PathVariable("id") String id){
        try {
            Compra d = data.obtener(Integer.parseInt(id));
            return ResponseEntity.status(200).body(d);
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }
}
