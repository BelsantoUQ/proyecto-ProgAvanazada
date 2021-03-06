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
public class SubastaBean {


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
            return subastaServicio.listar();
        } catch (Exception e) {
            e.printStackTrace();
        }return new ArrayList<>();
    }


    public String irADetalle(String id){
        return "detalleSubasta?faces-redirect=true&amp;subasta="+id;
    }



}
