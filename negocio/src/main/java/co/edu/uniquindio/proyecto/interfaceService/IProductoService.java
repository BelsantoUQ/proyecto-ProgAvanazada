package co.edu.uniquindio.proyecto.interfaceService;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface IProductoService {


    Producto guardar(Producto p) throws Exception;

    Producto actualizar(Producto p) throws Exception;

    void eliminar(String codigo) throws Exception;

    List<Producto> listar();

    void actualizarImagen(List<String> i, String codigo) throws Exception;

    void actualizarCategorias(List<Categoria> categorias, String codigo) throws Exception;

    List<Producto> listarProductosPuntos();

    List<Producto> listarPorVendedor(String username) throws Exception;

    Optional<Producto> obtenerProducto(String codigo) throws Exception;

    Optional<Usuario> obtenerVendedor(int codigo) throws Exception;

    List<Producto> obtenerFavoritos(Usuario user) throws Exception;

    List<Producto> obtenerFavoritosPorId(int user) throws Exception;

    Optional<Usuario> obtenerVendedorProducto(Producto p, int codigoVendedor) throws Exception;

    List<Producto> listarValidosPorCategoria(Categoria categoria);

    List<Producto> buscarIncluirCategoria(String nombre, Categoria categoria);

    List<Producto> buscarIncluirCiudad(String nombre, Ciudad ciudad) throws Exception;

    List<Producto> buscarPorCategoria(Categoria categoria);

    List<Producto> buscarPorCiudad(Ciudad ciudad) throws Exception;

    List<Producto> buscarPorPrecio(float i, float f);

    List<Producto> buscarIncluirCalificacion(String nombre, int calif) throws Exception;

    List<Producto> buscarProductos(String nombre);

    Categoria obtenerCategoria(String c);

    List<Categoria> listarCategorias();

    void comentarProducto(Comentario comentario);

    void actualizarComentarioProducto(Comentario comentario) throws Exception;

    List<Comentario> listarComentarios(String codigo);

    float promedioCalificaciones(String codigo) throws Exception;

    void asignarProductoCompra(DetalleCompra dc) throws Exception;

    Compra comprarProductos(Usuario usuario, ArrayList<ProductoCarrito> productos, String medioPago) throws Exception;

    Subasta subastarProducto(Producto p) throws Exception;

}
