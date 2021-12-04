package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.interfaceService.ICiudadService;
import co.edu.uniquindio.proyecto.interfaceService.IProductoService;
import co.edu.uniquindio.proyecto.interfaceService.IUsuarioServicio;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@ViewScoped
@Component
public class ModificarProductoBean implements Serializable {

    @Getter @Setter
    private Producto producto;

    @Autowired
    private IProductoService productoService;

    @Getter @Setter
    private ArrayList<String> imagenes;

    @Getter @Setter
    private List<Ciudad> ciudades;

    @Autowired
    private ICiudadService ciudadServicio;

    @Value("#{param['producto']}")
    private String codigoProducto;

    @Value("${upload.url}")
    private String urlUpload;

    @Value("#{seguridadBean.usuarioSesion}")
    private Usuario usuarioSesion;

    @PostConstruct
    public void inicializat(){
        this.producto = new Producto();
        this.imagenes = new ArrayList<>();
        try {
            this.producto = productoService.obtenerProducto(codigoProducto).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.ciudades = ciudadServicio.listar();
    }

    public String modificarProducto(){
        try {

                Usuario vendedor = usuarioSesion;
                producto.setVendedor(vendedor);

                if(!imagenes.isEmpty())
                    producto.getImagenRuta().addAll(imagenes);

                producto.setFechaLimite(LocalDate.now().plusMonths(1));
                productoService.actualizar(producto);
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Producto modificado satisfactoriamente");
                FacesContext.getCurrentInstance().addMessage("msj-bean",msg);
                return "/detalleProducto?faces-redirect=true&amp;producto="+producto.getCodigo();


        }catch (Exception e){
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("msj-bean", fm);
        }return "";
    }



    public void subirImagenes(FileUploadEvent event){
        UploadedFile imagen = event.getFile();
        String nombreImagen = subirImagen(imagen);
        if(nombreImagen!=null){
            imagenes.add(nombreImagen);
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
