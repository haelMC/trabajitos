package upeu.juliaca.eddy.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import upeu.juliaca.eddy.exceptions.ResourceNotFoundException;
import upeu.juliaca.eddy.models.Comentario;
import upeu.juliaca.eddy.repositories.ComentarioRepository;

@Service
@Transactional
public class ComentarioServiceImpl implements ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Override
    public Comentario save(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    @Override
    public List<Comentario> findAll() {
        return comentarioRepository.findAll();
    }

    @Override
    public Comentario findById(Long id) {
        return comentarioRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Comentario no encontrado con el id: " + id));
    }

    @Override
    public Comentario update(Comentario comentario, Long id) {
        Comentario existingComentario = comentarioRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Comentario no encontrado con el id: " + id));
        
        existingComentario.setTitulo(comentario.getTitulo());
        existingComentario.setDescripcion(comentario.getDescripcion());
        existingComentario.setAutor(comentario.getAutor());
        existingComentario.setFechaPublicacion(comentario.getFechaPublicacion());
        existingComentario.setFechaModificacion(comentario.getFechaModificacion());
        existingComentario.setCalificacion(comentario.getCalificacion());

        return comentarioRepository.save(existingComentario);
    }

    @Override
    public void delete(Long id) {
        Comentario comentario = comentarioRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Comentario no encontrado con el id: " + id));
        
        comentarioRepository.delete(comentario);
    }
}
