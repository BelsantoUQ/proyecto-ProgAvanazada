package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.interfaceService.IProductoService;
import co.edu.uniquindio.proyecto.interfaceService.IUsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.swing.text.html.Option;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Optional;


@Scope("session")
@Component
public class SeguridadBean implements Serializable {

    @Getter @Setter
    private int intentos;

    @Getter @Setter
    private boolean autenticado;

    @Getter @Setter
    private String email, password;

    @Getter @Setter
    private String medioPago;

    @Getter @Setter
    private ArrayList<String> mediosPagoDisponibles;

    @Autowired
    private IUsuarioServicio usuarioServicio;

    @Autowired
    private IProductoService productoServicio;

    @Getter @Setter
    private Usuario usuarioSesion;

    @Getter @Setter
    private float subtotal;

    @Getter @Setter
    private ArrayList<ProductoCarrito> productosCarrito;

    @PostConstruct
    public void inicializar(){

        autenticado=false;
        this.subtotal = 0;
        this.intentos = 0;
        this.productosCarrito = new ArrayList<>();
        this.mediosPagoDisponibles = getMediosPago();

    }

    private ArrayList<String> getMediosPago() {
        ArrayList<String> mediosPago = new ArrayList<>();
        mediosPago.add("MASTERCARD");
        mediosPago.add("AMEX");
        mediosPago.add("VISA");
        mediosPago.add("PSE");
        mediosPago.add("BALOTO");
        return mediosPago;
    }

    public String iniciarSesion(){
        if(!email.isEmpty() && !password.isEmpty()){
            try {
                Optional<Usuario> posible = usuarioServicio.iniciarSesion(email, password);
                if(!posible.isEmpty()){
                    autenticado=true;
                    usuarioSesion=posible.get();
                    return "/index?faces-redirect=true";
                }else{
                    posible = usuarioServicio.buscarPorEmail(email);

                    if(!posible.isEmpty()){
                        intentos++;
                    }

                }

                if (intentos==3 && !posible.isEmpty()){
                    usuarioServicio.recuperarContraseña(posible.get());
                    intentos=0;
                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta", "Multiples intentos de inicio, hemos enviado tu contraseña al correo");
                    FacesContext.getCurrentInstance().addMessage("login-bean", fm);
                }

            } catch (Exception e) {

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("login-bean", fm);
            }
        }
        return null;
    }
    public String cerrarSesion(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index?faces-redirect=true";
    }

    public void agregarAlCarrito(String codigo,float precio,String nombre, String imagenDestacada,int valor_en_puntos,float descuento, int unidades){
        ProductoCarrito product = new ProductoCarrito(codigo,nombre,imagenDestacada,1, unidades, valor_en_puntos,precio,descuento);

        if(!productosCarrito.contains(product)){

            productosCarrito.add(product);

            actualizarSubTotal();

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO,"ENHORABUENA!", "Se ha añadido al carrito");
            FacesContext.getCurrentInstance().addMessage("add-cart", fm);

        }else{
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN,"Alerta", "Ya se ha añadido al carrito con anterioridad");
            FacesContext.getCurrentInstance().addMessage("add-cart", fm);
        }
    }

    public void eliminarDelCarrito(int indice){

        productosCarrito.remove(indice);
        actualizarSubTotal();
        if(productosCarrito.isEmpty()){
            subtotal=0;
        }
    }

    public String comprar(){
        try {

            if(usuarioSesion!=null && !productosCarrito.isEmpty()){

                productoServicio.comprarProductos(usuarioSesion, productosCarrito, medioPago);
                productosCarrito.clear();
                subtotal=0F;
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "ENHORABUENA!","Compra realizada satisfactoriamente");
                FacesContext.getCurrentInstance().addMessage("compra-ms",fm);
                return "/index?faces-redirect=true";

            }
        }catch (Exception e){

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta",e.getMessage());
            FacesContext.getCurrentInstance().addMessage("compra-ms",fm);
        }
            return "";

    }

    public void actualizarSubTotal(){
        subtotal=0F;
        for (ProductoCarrito p:productosCarrito){
            subtotal+= p.getPrecioTotalConDcto();
        }

    }

    public String getContenidoBotonLogin(){
        if(autenticado){
            return usuarioSesion.getUsername()+": "+usuarioSesion.getPuntos()+"P.";
        }
        return "Login";
    }
    public String getContenidoBotonRegistro(){
        if(autenticado){
            return  "Ver Carrito";
        }
        return "Registrarme";
    }
    public String getIconoLogin(){
        if(autenticado){
            return "pi pi-thumbs-up";
        }
        return "pi pi-sign-in";
    }
    public String getIconoRegistrar(){
        if(autenticado){
            return "pi pi-shopping-cart";
        }
        return "pi pi-user-plus";
    }

    public String getLinkBotonRegistrar(){
        if(autenticado){
            return "/usuario/carrito?faces-redirect=true";
        }
        return "/registrarUsuario?faces-redirect=true";
    }
}
