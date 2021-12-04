package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.interfaceService.IProductoService;
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

@ViewScoped
@Component
public class MisProductosBean implements Serializable {

    @Autowired
    private IProductoService productoServicio;

    @Getter
    @Setter
    private List<Producto> productos;

    @Value("#{seguridadBean.usuarioSesion}")
    private Usuario usuarioSesion;

    @PostConstruct
    public void inicializar(){
        productos = listarTodo();
    }

    private List<Producto> listarTodo() {
        try {
            return productoServicio.listarPorVendedor(usuarioSesion.getUsername());
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("misP-bean",msg);
        }
        return new ArrayList<>();
    }


    public String modificarProducto(String id){
        return "/usuario/modificarProducto?faces-redirect=true&amp;producto="+id;
    }

    public String irADetalle(String id){
        return "/detalleProducto?faces-redirect=true&amp;producto="+id;
    }


}
