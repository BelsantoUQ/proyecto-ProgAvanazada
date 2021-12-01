package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.interfaceService.IProductoService;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private ProductoRepo data;

    @Autowired
    private ComentarioRepo comentarioRepo;


    @Override
    public Producto guardar(Producto p) throws Exception {
        Optional<Producto> validarP = data.findById(p.getCodigo());
        if(!validarP.isEmpty()){
            throw new Exception("Problemas al registrar: el codigo de producto ya fue asignado");
        }
        return data.save(p);
    }

    @Override
    public Producto actualizar(Producto p) throws Exception {
        Optional<Producto> validarP = data.findById(p.getCodigo());
        if(validarP.isEmpty()){

            throw new Exception("Problemas al actualizar: el codigo de producto no fue encontrado");
        }
        return data.save(p);
    }

    @Override
    public void eliminar(String codigo) throws Exception {
        Optional<Producto> aEliminar = data.findById(codigo);
        if(aEliminar.isEmpty()){

            throw new Exception("Problemas al eliminar: el codigo de producto no fue encontrado");
        }
        data.deleteById(codigo);
    }

    @Override
    public List<Producto> listar() {
        return data.listarValidos();
    }

    @Override
    public List<Producto> listarPorVendedor(String username) throws Exception {
        Optional<Usuario> vendedor = data.obtenerVendedorPorUsername(username);
        if (vendedor.isEmpty()){
            throw new Exception("No se encontro algún vendedor con este nombre de usuario");
        }

        return data.obtenerProductosVendedor(username);


    }

    @Override
    public Optional<Producto> obtenerProducto(String codigo) throws Exception {

        Optional<Producto> producto = data.findById(codigo);
        if (producto.isEmpty()){
            throw new Exception("No se ha encontrado algun producto con este codigo");
        }

        return producto;
    }

    @Override
    public Optional<Usuario> obtenerVendedor(int codigo) throws Exception {
        Optional<Usuario>vendedor = data.obtenerVendedorPorCodigo(codigo);
        if (vendedor.isEmpty()){
            throw new Exception("El codigo no pertenece a un vendedor existente");
        }
        return vendedor;
    }

    @Override
    public List<Producto> buscarProductos(String nombre, String[] filtros) {
        return data.findAllByNombreContains(nombre);
    }

    @Override
    public Categoria obtenerCategoria(String categoria){
        return Categoria.valueOf(categoria);
    }

    @Override
    public List<Categoria> listarCategorias() {
        return Arrays.asList(Categoria.values());
    }

    @Override
    public void comentarProducto(Comentario comentario) {
        comentario.setFechaComentario(LocalDateTime.now());
        comentarioRepo.save(comentario);
    }

    @Override
    public List<Comentario> listarComentarios(String codigo){

        return data.listarComentarios(codigo);
    }

    @Override
    public float promedioCalificaciones(String codigo) throws Exception{
        if(data.findById(codigo).isEmpty()){
            throw new Exception("No se logró encontrar el producto en la base de datos");
        }
        try {

        return data.obtenerPromedioCalificaciones(codigo);
        } catch (Exception e) {
            return 0;
        }
    }
}


