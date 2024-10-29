package upeu.juliaca.eddy.controllers;

import upeu.juliaca.eddy.models.Comentario;
import upeu.juliaca.eddy.services.ComentarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class ComentarioControllerTest {

    @InjectMocks
    private ComentarioController comentarioController;

    @Mock
    private ComentarioService comentarioService;

    private Comentario comentario;

    @BeforeEach
    public void setUp() {
        comentario = new Comentario();
        comentario.setId(1L);
        comentario.setTitulo("Buen Servicio");
        comentario.setDescripcion("El servicio fue muy bueno.");
        comentario.setAutor("Usuario XYZ");
        comentario.setCalificacion(4.5);
    }

    @Test
    public void testGetAllComentarios() {
        List<Comentario> listaComentarios = Arrays.asList(comentario);

        // Simular el comportamiento del servicio
        given(comentarioService.findAll()).willReturn(listaComentarios);

        // Llamar al controlador y verificar la respuesta
        ResponseEntity<List<Comentario>> response = comentarioController.getAllComentarios();
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("Buen Servicio", response.getBody().get(0).getTitulo());
    }

    @Test
    public void testCreateComentario() {
        given(comentarioService.save(Mockito.any(Comentario.class))).willReturn(comentario);

        ResponseEntity<Comentario> response = comentarioController.createComentario(comentario);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Buen Servicio", response.getBody().getTitulo());
    }

    @Test
    public void testGetComentarioByIdNotFound() {
        // Simular que el servicio lanza una excepción cuando el comentario no se encuentra
        given(comentarioService.findById(1L)).willThrow(new RuntimeException("Comentario no encontrado"));

        // Llamar al controlador y verificar que responde con un error 404
        Exception exception = assertThrows(RuntimeException.class, () -> {
            comentarioController.getComentarioById(1L);
        });

        assertEquals("Comentario no encontrado", exception.getMessage());
    }
}
