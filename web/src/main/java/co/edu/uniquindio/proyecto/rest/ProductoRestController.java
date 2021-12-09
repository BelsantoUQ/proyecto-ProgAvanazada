package co.edu.uniquindio.proyecto.rest;

import co.edu.uniquindio.proyecto.dto.Mensaje;
import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.interfaceService.ICiudadService;
import co.edu.uniquindio.proyecto.interfaceService.IProductoService;
import co.edu.uniquindio.proyecto.interfaceService.IUsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/productos")
public class ProductoRestController {

    @Autowired
    private IProductoService productoServicio;

    @Autowired
    private ICiudadService ciudadServicio;

    @GetMapping
    public ResponseEntity<?> listar(){
        List<Producto> list= productoServicio.listar();
        return ResponseEntity.status(200).body(list);
    }

    @GetMapping("obtenerP/{id}")
    public ResponseEntity<?> obtener(@PathVariable("id") String idP){
        try {
            Producto producto = productoServicio.obtenerProducto(idP).get();
            return ResponseEntity.status(200).body(producto);
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Producto p){
        try {
            Producto producto =  productoServicio.guardar(p);
            return ResponseEntity.status(200).body(producto);
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<?> actualizar(@RequestBody Producto p){
        try {
            Producto producto = productoServicio.actualizar(p);
            return ResponseEntity.status(200).body(new Mensaje("El usuario fue actualizado"));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable("id") String id){
        try {
            productoServicio.eliminar(id);
            return ResponseEntity.status(200).body(new Mensaje("El usuario fue eliminado"));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @GetMapping("puntos/")
    public ResponseEntity<?> obtenerPuntos(){
        try {
            List<Producto> list = productoServicio.listarProductosPuntos();
            return ResponseEntity.status(200).body((list));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @GetMapping("favoritos/{id}")
    public ResponseEntity<?> obtenerFavoritos(@PathVariable("id") int id){
        try {
            List<Producto> list = productoServicio.obtenerFavoritosPorId(id);
            return ResponseEntity.status(200).body((list));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @GetMapping("vendedor/{id}")
    public ResponseEntity<?> obtenerVendedor(@PathVariable("id") int id){
        try {
            Usuario u = productoServicio.obtenerVendedor(id).get();
            return ResponseEntity.status(200).body((u));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @GetMapping("precios/{valor1, valor2}")
    public ResponseEntity<?> obtenerPorPrecio(@PathVariable("valo1") float valo1,@PathVariable("valo2") float valo2){
        try {
            List<Producto> u = productoServicio.buscarPorPrecio(valo1, valo2);
            return ResponseEntity.status(200).body((u));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @GetMapping("/categoriaProductos/{id}")
    public ResponseEntity<?> obtenerPorCategoria(@PathVariable("id") int id){
        try {
            Categoria categoria = Categoria.values()[id];
            List<Producto> u = productoServicio.buscarPorCategoria(categoria);
            return ResponseEntity.status(200).body((u));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @GetMapping("ciudadProductos/{id}")
    public ResponseEntity<?> obtenerPorCiudad(@PathVariable("id") int id){
        try {
            Ciudad city = ciudadServicio.obtenerCiudad(id);
            List<Producto> u = productoServicio.buscarPorCiudad(city);
            return ResponseEntity.status(200).body((u));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

}
