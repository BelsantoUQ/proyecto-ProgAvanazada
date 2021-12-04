package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.entidades.Subasta_Usuario;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.interfaceService.ISubastaService;
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

@ViewScoped
@Component
public class MisSubastasBean {


    @Autowired
    private ISubastaService subastaServicio;

    @Getter
    @Setter
    private List<Subasta> subastas;


    @Getter
    @Setter
    private Usuario usuarioSubata;

    @Getter
    @Setter
    private Subasta_Usuario subasta_usuario;

    @Value("#{seguridadBean.usuarioSesion}")
    private Usuario usuarioSesion;

    @PostConstruct
    public void inicializar(){
        subastas = listarTodo();
    }

    public List<Subasta> listarTodo() {
        try {
            return subastaServicio.listarPorVendedor(usuarioSesion);
        } catch (Exception e) {
            e.printStackTrace();
        }return new ArrayList<>();
    }

    public Subasta_Usuario obtenerSubastaUsuario(Usuario user){
        try {
            return subastaServicio.obtenerDetalleSubastaPorUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }return null;

    }

    public Usuario obtenerUserMejorOferta(int codigoSubasta){
        try {
            Optional<Usuario> user_M_O = subastaServicio.obtenerUsuarioMejor(codigoSubasta);
            if (!user_M_O.isEmpty()){
                return user_M_O.get();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }return null;
    }

    public void eliminar(int codigo){
        try {
            subastaServicio.eliminar(codigo);

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Eliminado exitosamente");
            FacesContext.getCurrentInstance().addMessage("sub-eliminar", fm);
        } catch (Exception e) {

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("sub-eliminar", fm);
        }
    }

}
