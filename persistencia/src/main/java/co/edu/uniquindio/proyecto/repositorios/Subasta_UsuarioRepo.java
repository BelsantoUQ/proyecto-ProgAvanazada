package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.entidades.Subasta_Usuario;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Subasta_UsuarioRepo extends JpaRepository<Subasta_Usuario, Integer> {

    @Query("select su.valor from Subasta_Usuario su where su.subasta.codigo = :codigo and su.valor = (select max(su2.valor) from Subasta_Usuario su2 where su2.subasta.codigo = :codigo)")
    float obtenerOfertaMayor(int codigo);

    @Query("select su.usuarioSubasta from Subasta_Usuario su where su.subasta.codigo = :codigo and su.valor = (select max(su2.valor) from Subasta_Usuario su2 where su2.subasta.codigo = :codigo)")
    Optional<Usuario> obtenerUsuarioGanador(int codigo);

    Optional<Subasta_Usuario> findSubasta_UsuarioByUsuarioSubasta(Usuario usuario);

    List<Subasta_Usuario> findAllByUsuarioSubasta(Usuario u);

    @Query("select su from Subasta_Usuario su where :u = su.usuarioSubasta and :s = su.subasta and :valor = su.valor")
    Optional<Subasta_Usuario> encontrarSubastaRealizada(Usuario u, Subasta s, float valor);

}
