package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.interfaceService.IUsuarioServicio;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService implements IUsuarioServicio {

    @Autowired
    private UsuarioRepo data;

    @Autowired
    private ProductoRepo productoRepo;

    @Autowired
    private SendMailService mailSenderService;

    @Override
    public Usuario guardar(Usuario u) throws Exception {
        int validacion = validarExistente(u);
        switch (validacion){
            case 1:
            throw new Exception("Imposible realizar operacion");
            case 2:
            throw new Exception("El correo electrónico del usuario ya existe");
            case 3:
            throw new Exception("El nombre de usuario elegido ya existe");
        }
        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        u.setPassword(passwordEncryptor.encryptPassword(u.getPassword()));
        return  data.save(u);

    }

    @Override
    public Usuario actualizar(Usuario u) throws Exception {

        Optional<Usuario> buscado= data.findById(u.getCodigo());
        if(buscado.isEmpty()){
            throw new Exception("El código del usuario ya existe");
        }

        return  data.save(u);

    }

    @Override
    public void eliminarFavorito(Usuario u, Producto p) throws Exception {

        Optional<Usuario> buscado= data.findById(u.getCodigo());
        Optional<Producto> buscado2= productoRepo.findById(p.getCodigo());
        if(buscado.isEmpty() && buscado2.isEmpty()){
            throw new Exception("El código del usuario ya existe");
        }else{
            data.eliminarFavorito(u.getCodigo(), p.getCodigo());
        }



    }

    @Override
    public int validarExistente(Usuario u){
        Optional<Usuario> buscado= data.findById(u.getCodigo());
        Optional<Usuario> buscado2= data.findByEmail(u.getEmail());
        Optional<Usuario> buscado3= data.findByUsername(u.getUsername());
        if(!buscado.isEmpty()){
            return 1;
        }
        if(!buscado2.isEmpty()){
            return 2;
        }
        if(!buscado3.isEmpty()){
            return 3;
        }

        return 0;
    }

    @Override
    public void eliminar(int codigo) throws Exception {
        Optional<Usuario> buscado= data.findById(codigo);
        if(buscado.isEmpty()){
            throw new Exception("El codigo esta mal escrito o el usuario no existe");
        }
        data.deleteById(codigo);
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email){
        Optional<Usuario> buscado = data.findByEmail(email);

        return buscado;
    }

    @Override
    public Optional<Usuario> obtenerUsuario(int codigo) throws Exception{
        Optional<Usuario> buscado = data.findById(codigo);
        if (buscado.isEmpty()){
            throw new Exception("El codigo ingresado no corresponde a algún usuario");
        }
        return buscado;
    }

    @Override
    public Optional<Usuario> iniciarSesion(String email, String password) {
        Optional<Usuario> u = data.findByEmail(email);
        if(!u.isEmpty()) {
            StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
            if (passwordEncryptor.checkPassword(password, u.get().getPassword())) {
                return u;
            }
        }return Optional.empty();
    }

    @Override
    public void recuperarContraseña(Usuario usuario) {
        mailSenderService.sendMail("elitesantymetal@gmail.com",usuario.getEmail(),"RECUPERACION DE CONTRASEÑA"
                ,"Buenos dias, tardes o noches, este mensaje es enviado para indicar que ha intentado ingresar " +
                        "3 veces sin exito así que aqui está su contraseña"+usuario.getPassword());
    }

    @Override
    public List<Usuario> listar() {
        return data.findAll();
    }

    @Override
    public List<Producto> listarProductosFavoritos(String email) throws Exception{
        Optional<Usuario>buscado = buscarPorEmail(email);
        if (buscado.isEmpty()){
            throw new Exception("No se ha encontrado email");
        }
        return data.obtenerProductosFavoritos(email);
    }
}
