package co.edu.uniquindio.proyecto.bean;


import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.interfaceService.ICiudadService;
import co.edu.uniquindio.proyecto.interfaceService.IUsuarioServicio;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class UsuarioBean implements Serializable {

    @Getter @Setter
    private Usuario usuario;

    @Autowired
    private IUsuarioServicio usuarioServicio;

    @Getter @Setter
    private String foto;

    @Getter @Setter
    private List<Ciudad> ciudades;

    @Autowired
    private ICiudadService ciudadServicio;

    @Value("${upload.url}")
    private String urlUpload;

    @PostConstruct
    public void inicializar(){

        usuario = new Usuario();
        this.foto = "";
        this.ciudades = ciudadServicio.listar();
    }

    public String registrarUsuario(){

        try {
            if (!foto.equals("")){

                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Alerta","Registro Exitoso");
                FacesContext.getCurrentInstance().addMessage(null,msg);
                usuario.setRutaFoto(foto);
                int codigo = 1+usuarioServicio.listar().size();
                usuario.setCodigo(codigo);
                usuarioServicio.guardar(usuario);
                return "/index?faces-redirect=true";
            }
        } catch (Exception e) {

            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta","Registro Fallido: "+e);
            FacesContext.getCurrentInstance().addMessage(null,msg);
        }
        return "";
    }

    public void subirImagenes(FileUploadEvent event){
        UploadedFile imagen = event.getFile();
        String nombreImagen = subirImagen(imagen);
        if(nombreImagen!=null){
            foto=(nombreImagen);
        }
    }

    private String subirImagen(UploadedFile imagen) {
        try {
            File archivo = new File(urlUpload + "/" + imagen.getFileName());
            OutputStream outputStream = new FileOutputStream(archivo);
            IOUtils.copy(imagen.getInputStream(), outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imagen.getFileName();
    }
}
