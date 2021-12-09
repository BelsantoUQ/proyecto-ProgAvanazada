package co.edu.uniquindio.proyecto.rest;

import co.edu.uniquindio.proyecto.entidades.Mensaje;
import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.interfaceService.IMensajeService;
import co.edu.uniquindio.proyecto.interfaceService.ISubastaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/subastas")
public class SubastaRestController {

    @Autowired
    private ISubastaService data;

    @GetMapping
    public ResponseEntity<?> listar(){
        List<Subasta> list= data.listar();
        return ResponseEntity.status(200).body(list);
    }

    @GetMapping("obtener/{id}")
    public ResponseEntity<?> obtener(@PathVariable("id") String id){
        try {
            Subasta d = data.obtenerSubasta(Integer.parseInt(id));
            return ResponseEntity.status(200).body(d);
        }catch (Exception e){
            return ResponseEntity.status(500).body(new co.edu.uniquindio.proyecto.dto.Mensaje(e.getMessage()));
        }
    }
}
