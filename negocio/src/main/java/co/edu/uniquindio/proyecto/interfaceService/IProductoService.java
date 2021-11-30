package co.edu.uniquindio.proyecto.interfaceService;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;

import java.util.List;
import java.util.Optional;

public interface IProductoService {


    Producto guardar(Producto p) throws Exception;

    Producto actualizar(Producto p) throws Exception;

    void eliminar(String codigo) throws Exception;

    List<Producto> listar();

    List<Producto> listarPorVendedor(String username) throws Exception;

    Optional<Producto> obtenerProducto(String codigo) throws Exception;

    Optional<Usuario> obtenerVendedor(int codigo) throws Exception;

    List<Producto> buscarProductos(String nombre, String[] filtros);

    Categoria obtenerCategoria(String c) throws Exception;

    List<Categoria> listarCategorias();
}
