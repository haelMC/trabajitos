package upeu.juliaca.eddy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import upeu.juliaca.eddy.models.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
}
