package upeu.juliaca.eddy.services;

import upeu.juliaca.eddy.models.Comentario;
import upeu.juliaca.eddy.repositories.ComentarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class ComentarioServiceTest {

    @InjectMocks
    private ComentarioServiceImpl comentarioService;

    @Mock
    private ComentarioRepository comentarioRepository;

    private Comentario comentario;

    @BeforeEach
    public void setUp() {
        comentario = new Comentario();
        comentario.setId(1L);
        comentario.setTitulo("Buen Servicio");
        comentario.setDescripcion("Con experiencia excelente.");
        comentario.setAutor("Usuario XYZ");
        comentario.setCalificacion(4.5);
    }

    @Test
    public void testGetComentarioById() {
        // Simulamos que el repositorio devuelve un comentario
        given(comentarioRepository.findById(1L)).willReturn(Optional.of(comentario));

        Comentario result = comentarioService.findById(1L);
        
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Buen Servicio", result.getTitulo());
    }

    @Test
    public void testCreateComentario() {
        given(comentarioRepository.save(Mockito.any(Comentario.class))).willReturn(comentario);

        Comentario result = comentarioService.save(comentario);
        
        assertNotNull(result);
        assertEquals("Buen Servicio", result.getTitulo());
    }
}
