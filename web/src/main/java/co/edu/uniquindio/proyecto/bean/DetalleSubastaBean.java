package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.interfaceService.IProductoService;
import co.edu.uniquindio.proyecto.interfaceService.ISubastaService;
import co.edu.uniquindio.proyecto.interfaceService.ISubastaUsuarioService;
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
import java.util.List;


@Component
@ViewScoped
public class DetalleSubastaBean implements Serializable {

    @Autowired
    private IProductoService productoServicio;

    @Autowired
    private ISubastaService subastaServicio;

    @Autowired
    private ISubastaUsuarioService subastaUsuarioService;

    @Autowired
    private IUsuarioServicio usuarioServicio;

    @Value("#{param['subasta']}")
    private String codigoSubasta;

    @Getter
    @Setter
    private boolean vendedorAutenticado;

    @Getter @Setter
    private Subasta subasta;

    @Getter @Setter
    private Producto producto;

    @Getter @Setter
    private float mejorOferta;


    @Getter @Setter
    private float ofertaActual;

    @Getter @Setter
    private Usuario userMejorOferta;

    @Getter @Setter
    private Comentario nuevoComentario;

    @Value("#{seguridadBean.usuarioSesion}")
    private Usuario usuarioSesion;

    @PostConstruct
    public void inicializar(){
        if(codigoSubasta!=null && !codigoSubasta.isEmpty()){
            nuevoComentario = new Comentario();
            this.ofertaActual =0F;
            try {
                subasta = subastaServicio.obtenerSubasta(Integer.parseInt(codigoSubasta));
                producto = subasta.getProductoEnSubasta();
                mejorOferta = subastaServicio.obtenerMejorOferta(subasta.getCodigo());
                userMejorOferta = subastaServicio.obtenerUsuarioMejor(subasta.getCodigo()).get();
                vendedorAutenticado = validarVendedor(producto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void ofertar(){

        if(ofertaActual>0) {
            try {
                if (ofertaActual > mejorOferta) {
                    subastaServicio.ofertar(this.subasta, usuarioSesion, ofertaActual);

                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Se ha enviado la oferta");
                    FacesContext.getCurrentInstance().addMessage("oferta-user", fm);
                } else {

                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", "La oferta debe ser mayor");
                    FacesContext.getCurrentInstance().addMessage("oferta-user", fm);
                }
            } catch (Exception e) {

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("oferta-user", fm);
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
        return "pi pi-thumbs-up";
    }


}