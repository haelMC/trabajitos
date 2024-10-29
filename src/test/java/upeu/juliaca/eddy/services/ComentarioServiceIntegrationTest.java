package upeu.juliaca.eddy.services;

import upeu.juliaca.eddy.models.Comentario;
import upeu.juliaca.eddy.repositories.ComentarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ComentarioServiceIntegrationTest {

    @Autowired
    private ComentarioService comentarioService;

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
        
        comentarioService.save(comentario);
    }

    @Test
    public void testFindAll() {
        List<Comentario> comentarios = comentarioService.findAll();
        assertEquals(1, comentarios.size());
        assertEquals("Buen Servicio", comentarios.get(0).getTitulo());
    }

    @Test
    public void testFindById() {
        Comentario comentarioEncontrado = comentarioService.findById(comentario.getId());
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

        Comentario createdComentario = comentarioService.save(nuevoComentario);
        assertNotNull(createdComentario);
        assertEquals("Excelente Producto", createdComentario.getTitulo());
    }

    @Test
    public void testUpdateComentario() {
        Comentario actualizado = new Comentario();
        actualizado.setTitulo("Producto Mejorado");
        actualizado.setDescripcion("La calidad del producto mejoró considerablemente.");
        actualizado.setAutor("Usuario XYZ");
        actualizado.setFechaPublicacion(new Date());
        actualizado.setFechaModificacion(new Date());
        actualizado.setCalificacion(4.8);

        Comentario updatedComentario = comentarioService.update(actualizado, comentario.getId());
        assertEquals("Producto Mejorado", updatedComentario.getTitulo());
    }

    @Test
    public void testDeleteComentario() {
        comentarioService.delete(comentario.getId());
        List<Comentario> comentarios = comentarioService.findAll();
        assertEquals(0, comentarios.size());
    }
}
