package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Subasta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubastaRepo extends JpaRepository<Subasta, Integer>{

    @Query("select s from Subasta s where :categoria member of s.productoEnSubasta.categorias and s.fechaLimite > current_timestamp")
    List<Subasta> listarSubastasPorCategoria(Categoria categoria);

}
