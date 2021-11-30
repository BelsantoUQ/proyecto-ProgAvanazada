package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Subasta_Usuario;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Subasta_UsuarioRepo extends JpaRepository<Subasta_Usuario, Integer> {


    @Query("select su.usuarioSubasta from Subasta_Usuario su where su.subasta.codigo= :codigo order by su.valor")
    List<Usuario> obtenerOfertasUsuario(int codigo);

    @Query("select su.usuarioSubasta from Subasta_Usuario su where su.subasta.codigo = :codigo and su.valor = (select max(su2.valor) from Subasta_Usuario su2 where su2.subasta.codigo = :codigo)")
    Optional<Usuario> obtenerUsuarioGanador(int codigo);




}
