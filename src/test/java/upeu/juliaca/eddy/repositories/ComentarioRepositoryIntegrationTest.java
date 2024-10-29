package upeu.juliaca.eddy.repositories;

import upeu.juliaca.eddy.models.Comentario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ComentarioRepositoryIntegrationTest {

    @Autowired
    private ComentarioRepository comentarioRepository;

    private Comentario comentario;

    @BeforeEach
    public void setUp() {
        // Limpiar la base de datos antes de cada prueba
        comentarioRepository.deleteAll();
        
        // Crear un comentario de ejemplo
        comentario = new Comentario();
        comentario.setTitulo("Buen Servicio");
        comentario.setDescripcion("El servicio fue excelente.");
        comentario.setAutor("Usuario XYZ");
        comentario.setFechaPublicacion(new Date());
        comentario.setFechaModificacion(new Date());
        comentario.setCalificacion(4.5);
        
        comentarioRepository.save(comentario);
    }

    @Test
    public void testFindAll() {
        List<Comentario> comentarios = comentarioRepository.findAll();
        assertEquals(1, comentarios.size());
        assertEquals("Buen Servicio", comentarios.get(0).getTitulo());
    }

    @Test
    public void testFindById() {
        Comentario comentarioEncontrado = comentarioRepository.findById(comentario.getId()).orElse(null);
        assertNotNull(comentarioEncontrado);
        assertEquals("Buen Servicio", comentarioEncontrado.getTitulo());
    }

    @Test
    public void testCreateComentario() {
        Comentario nuevoComentario = new Comentario();
        nuevoComentario.setTitulo("Excelente Producto");
        nuevoComentario.setDescripcion("El producto cumplió mis expectativas.");
        nuevoComentario.setAutor("Usuario ABC");
        nuevoComentario.setFechaPublicacion(new Date());
        nuevoComentario.setFechaModificacion(new Date());
        nuevoComentario.setCalificacion(5.0);

        Comentario createdComentario = comentarioRepository.save(nuevoComentario);
        assertNotNull(createdComentario);
        assertEquals("Excelente Producto", createdComentario.getTitulo());
    }

    @Test
    public void testDeleteComentario() {
        comentarioRepository.delete(comentario);
        List<Comentario> comentarios = comentarioRepository.findAll();
        assertEquals(0, comentarios.size());
    }
}
