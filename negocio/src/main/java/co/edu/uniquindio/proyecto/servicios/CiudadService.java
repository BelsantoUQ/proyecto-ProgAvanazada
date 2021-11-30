package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.interfaceService.ICiudadService;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CiudadService implements ICiudadService {

    @Autowired
    private CiudadRepo data;

    @Override
    public Ciudad guardar(Ciudad c) throws Exception {
        Optional <Ciudad> ciudad = data.findById(c.getCodigo());
        if(!ciudad.isEmpty()){
            throw new Exception("Ya hay una ciudad registrada con este codigo");
        }
        return data.save(c);
    }

    @Override
    public void eliminar(int codigo) throws Exception {
        Optional <Ciudad> ciudad = data.findById(codigo);
        if(!ciudad.isEmpty()){
            throw new Exception("No se encontro una ciudad registrada con este codigo");
        }
        data.deleteById(codigo);
    }

    @Override
    public Ciudad obtenerCiudad(int codigo) throws Exception {

           return data.findById(codigo).orElseThrow(() -> new Exception("No se encontro una ciudad registrada con este codigo"));


    }

    @Override
    public List<Ciudad> listar() {
        return data.findAll();
    }
}
