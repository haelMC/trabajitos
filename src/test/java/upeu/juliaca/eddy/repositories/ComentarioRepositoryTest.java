package upeu.juliaca.eddy.repositories;

import upeu.juliaca.eddy.models.Comentario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class ComentarioRepositoryTest {

    @Mock
    private ComentarioRepository comentarioRepository;

    private Comentario comentario;

    @BeforeEach
    public void setUp() {
        comentario = new Comentario();
        comentario.setId(1L);
        comentario.setTitulo("Buen Servicio");
        comentario.setDescripcion("El servicio fue excelente.");
        comentario.setAutor("Usuario XYZ");
        comentario.setCalificacion(4.5);
    }

    @Test
    public void testFindById() {
        given(comentarioRepository.findById(1L)).willReturn(Optional.of(comentario));

        Optional<Comentario> result = comentarioRepository.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    public void testFindAll() {
        List<Comentario> comentarios = List.of(comentario);

        given(comentarioRepository.findAll()).willReturn(comentarios);

        List<Comentario> result = comentarioRepository.findAll();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }
}
