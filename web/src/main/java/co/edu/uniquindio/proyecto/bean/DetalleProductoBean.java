package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.interfaceService.IProductoService;
import co.edu.uniquindio.proyecto.interfaceService.IUsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@ViewScoped
public class DetalleProductoBean implements Serializable {

    @Autowired
    private IProductoService productoServicio;

    @Autowired
    private IUsuarioServicio usuarioServicio;

    @Value("#{param['producto']}")
    private String codigoProducto;

    @Getter @Setter
    private boolean vendedorAutenticado;

    @Getter @Setter
    private Producto producto;

    @Getter @Setter
    private String respuesta;

    @Getter @Setter
    private List<Comentario> comentarios;

    @Getter @Setter
    private Comentario nuevoComentario;

    @Getter @Setter
    private int calificacionPromedio;

    @Value("#{seguridadBean.usuarioSesion}")
    private Usuario usuarioSesion;

    @PostConstruct
    public void inicializar(){
        if(codigoProducto!=null && !codigoProducto.isEmpty()){
            nuevoComentario = new Comentario();
            try {
                producto = productoServicio.obtenerProducto(codigoProducto).get();
                vendedorAutenticado = validarVendedor(producto);
                this.comentarios = producto.getComentarios();
                this.calificacionPromedio = (int) productoServicio.promedioCalificaciones(codigoProducto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Boolean validarVendedor(Producto producto) {
        try {
            if(usuarioSesion!=null){
                int codigo = usuarioSesion.getCodigo();
                Usuario userAux = new Usuario();
                userAux = productoServicio.obtenerVendedorProducto(producto,codigo).get();

                if (userAux.getCodigo()==codigo){
                    return true;
                }else {
                    return false;
                }
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }

    public String getIconBoton(){
        if (vendedorAutenticado){
            return "pi pi-dollar";
        }
        return "pi pi-heart-fill";
    }

    public void crearComentario(){

        if(usuarioSesion!=null){
            try {
                nuevoComentario.setProductoC(producto);
                nuevoComentario.setUserComent(usuarioSesion);
                productoServicio.comentarProducto(nuevoComentario);
                this.comentarios.add(nuevoComentario);
                nuevoComentario = new Comentario();

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Se ha enviado la pregunta");
                FacesContext.getCurrentInstance().addMessage("msj-pregunta", fm);
            } catch (Exception e) {

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("msj-pregunta", fm);
            }
        }

    }

    public void crearRespuesta(Comentario comentario){

        if(usuarioSesion!=null){
            try {
                comentario.setRespuesta(respuesta);
                productoServicio.actualizarComentarioProducto(comentario);
                respuesta="";

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Se ha enviado la respuesta");
                FacesContext.getCurrentInstance().addMessage("msj-respuesta", fm);

            } catch (Exception e) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("msj-respuesta", fm);
            }
        }


    }

    public String mostrarContenidoComentar(){
        if(!vendedorAutenticado){
            return "Hacer Comentario";
        }else return "Tu Producto";
    }

    public String getBotonAgregar(){
        int unidades = producto.getUnidades();
        if(vendedorAutenticado){

            if(unidades>0){
                try {
                    productoServicio.subastarProducto(producto);
                    unidades--;
                    producto.setUnidades(unidades);
                    productoServicio.actualizar(producto);
                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "ENHORABUENA!", "Se ha añadido al subastas");
                    FacesContext.getCurrentInstance().addMessage("add-cart", fm);
                } catch (Exception e) {

                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                    FacesContext.getCurrentInstance().addMessage("add-cart", fm);
                }
                return "/usuario/misSubastas?faces-redirect=true";
            }
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", "No hay Stock de este producto segun nuestra BD");
            FacesContext.getCurrentInstance().addMessage("add-cart", fm);
        }else{

            try {
                producto.getUsuariosPotenciales().add(usuarioSesion);
                productoServicio.actualizar(producto);



            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        return "";
    }

    public String getContenidoBoton(){
        if(vendedorAutenticado){
            return "Subastar";
        }
        return "Like";
    }

}
