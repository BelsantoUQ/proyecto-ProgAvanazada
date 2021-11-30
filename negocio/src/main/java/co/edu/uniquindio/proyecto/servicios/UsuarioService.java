package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.interfaceService.IUsuarioServicio;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements IUsuarioServicio {

    @Autowired
    private UsuarioRepo data;

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
    public Optional<Usuario> buscarPorEmail(String email) throws Exception{
        Optional<Usuario> buscado = data.findByEmail(email);
        if (buscado.isEmpty()){
            throw new Exception("No se ha encontrado email");
        }
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
    public Usuario iniciarSesion(String email, String password) throws Exception {
        return data.findByEmailAndPassword(email, password).orElseThrow( () -> new Exception("Los datos de autenticacion son incorrectos"));
    }

    @Override
    public List<Usuario> listar() {
        return data.findAll();
    }

    @Override
    public List<Producto> listarProductosFavoritos(String email) throws Exception{
        Optional<Usuario>buscado = buscarPorEmail(email);
        return data.obtenerProductosFavoritos(email);
    }
}
