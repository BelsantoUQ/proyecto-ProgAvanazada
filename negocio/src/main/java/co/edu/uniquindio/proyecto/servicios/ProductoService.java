package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.interfaceService.IProductoService;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private ProductoRepo data;

    @Autowired
    private ComentarioRepo comentarioRepo;

    @Autowired
    private SubastaRepo subastaRepo;

    @Autowired
    private CiudadRepo ciudadRepo;

    @Autowired
    private DetalleCompraRepo detalleCompraRepo;

    @Autowired
    private CompraRepo compraRepo;

    @Autowired
    private SendMailService mailSenderService;

    @Autowired
    private UsuarioRepo usuarioRepo;


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

            throw new Exception("Problemas al actualizar produto: "+p.getCodigo()+" el codigo de producto no fue encontrado");
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
    public Optional<Usuario> obtenerVendedorProducto(Producto p, int codigoVendedor) throws Exception {
        Optional<Usuario>vendedor = data.obtenerVendedorPorProducto(p,codigoVendedor);
        if (vendedor.isEmpty()){
            throw new Exception("El codigo no pertenece a un vendedor existente");
        }
        return vendedor;
    }

    @Override
    public List<Producto> listarValidosPorCategoria(Categoria categoria) {
        return data.listarValidosPorCategoria(categoria);
    }

    @Override
    public List<Producto> buscarIncluirCategoria(String nombre, Categoria categoria) {
        return  data.buscarProductoCategoria(categoria,nombre);
    }

    @Override
    public List<Producto> buscarIncluirCiudad(String nombre, Ciudad ciudad) throws Exception {
        if (!ciudadRepo.findById(ciudad.getCodigo()).isEmpty()) {
            return data.buscarProductoCiudad(ciudad, nombre);
        }else throw new Exception("No se encontró la ciudad");
    }

    @Override
    public List<Producto> buscarIncluirCalificacion(String nombre, int calificacion) throws Exception {
        List<Producto> lista1 = data.buscarProductoValido(nombre);
        List<Producto> listADevolver = new ArrayList<>();
        int calificacionAux;
        for (int i = 0; i < lista1.size(); i++) {
            calificacionAux = (int)data.obtenerPromedioCalificaciones(lista1.get(i).getCodigo());
            if (calificacionAux==calificacion){
                listADevolver.add(lista1.get(i));
            }

        }
        return listADevolver;
    }

    @Override
    public List<Producto> buscarProductos(String nombre) {
        return data.buscarProductoValido(nombre);
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
    public void actualizarComentarioProducto(Comentario comentario) throws Exception{
        if (!comentarioRepo.findById(comentario.getCodigo()).isEmpty()){
        comentarioRepo.save(comentario);
        }else {
         throw new Exception("Error no se encontro el comentario");
        }
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

    @Override
    public void asignarProductoCompra(DetalleCompra dc) throws Exception {
            detalleCompraRepo.save(dc);
    }

    @Override
    public Compra comprarProductos(Usuario usuario, ArrayList<ProductoCarrito> productos, String medioPago) throws Exception{

        try {


            Compra c = new Compra();
            c.setFechaCompra(LocalDateTime.now(ZoneId.of("America/Bogota")));
            c.setMedioPago(medioPago);
            c.setComprador(usuario);
            Compra compraGuardada = compraRepo.save(c);
            int puntos = usuario.getPuntos();

            mailSenderService.sendMail("elitesantymetal@gmail.com",usuario.getEmail(),"CONFIRMACION DE COMPRA","Buenos dias, tardes o noches, este mensaje es enviado para indicar que ha realizado la compra exitosamente");

            DetalleCompra dc;

            for (ProductoCarrito pc : productos) {
                puntos+=(int)(pc.getPrecioTotalConDcto()/1000);
                dc = new DetalleCompra();
                dc.setCompra(compraGuardada);
                dc.setPrecioProducto(pc.getPrecioConDcto());
                dc.setUnidades(pc.getUnidades());
                Producto producto = data.findById(pc.getCodigo()).get();
                dc.setProductoCompra(producto);
                //
                detalleCompraRepo.save(dc);
                producto.setUnidades(pc.getUnidadesMax()- pc.getUnidades());
                this.actualizar(producto);
            }
            usuario.setPuntos(puntos);
            usuarioRepo.save(usuario);
            return compraGuardada;

        }catch (Exception e){
            throw new Exception("Error no se pudo realizar la compra: "+e.getMessage());
        }
    }

    @Override
    public Subasta subastarProducto(Producto p) throws Exception {
        if (!data.findById(p.getCodigo()).isEmpty()) {
            Subasta s = new Subasta(LocalDateTime.now().plusMonths(1), p);
            return subastaRepo.save(s);
        }else throw new Exception("No se encontró el producto imposible crear la subasta");
    }


}


