package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepo extends JpaRepository<Chat, Integer>{

    @Query("select ch from Chat ch where ch.chatProducto.vendedor.codigo = :codigo")
    List<Chat> findAllByVendedor(int codigo);

}
