package upeu.juliaca.eddy.services;

import java.util.List;

import upeu.juliaca.eddy.models.Comentario;

public interface ComentarioService {
    Comentario save(Comentario comentario);
    List<Comentario> findAll();
    Comentario findById(Long id);
    Comentario update(Comentario comentario, Long id);
    void delete(Long id);
}
