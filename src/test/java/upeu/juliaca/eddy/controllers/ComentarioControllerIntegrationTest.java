package upeu.juliaca.eddy.controllers;

import upeu.juliaca.eddy.models.Comentario;
import upeu.juliaca.eddy.repositories.ComentarioRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.Date;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ComentarioControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private ComentarioRepository comentarioRepository;

    private Comentario comentario;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        
        // Limpiar la base de datos antes de cada prueba
        comentarioRepository.deleteAll();
        
        // Crear un comentario para usar en las pruebas
        comentario = new Comentario();
        comentario.setTitulo("Buen Servicio");
        comentario.setDescripcion("El servicio fue muy bueno y rápido.");
        comentario.setAutor("Usuario XYZ");
        comentario.setFechaPublicacion(new Date());
        comentario.setFechaModificacion(new Date());
        comentario.setCalificacion(4.5);
        comentarioRepository.save(comentario);
    }

    @Test
    public void testGetAllComentarios() {
        given()
            .when().get("/api/comentarios")
            .then()
            .statusCode(200)
            .body("size()", is(1))
            .body("[0].titulo", equalTo("Buen Servicio"));
    }

    @Test
    public void testGetComentarioById() {
        Long id = comentario.getId();
        given()
            .when().get("/api/comentarios/{id}", id)
            .then()
            .statusCode(200)
            .body("titulo", equalTo("Buen Servicio"))
            .body("autor", equalTo("Usuario XYZ"));
    }

    @Test
    public void testCreateComentario() {
        Comentario newComentario = new Comentario();
        newComentario.setTitulo("Excelente Producto");
        newComentario.setDescripcion("El producto cumplió todas mis expectativas.");
        newComentario.setAutor("Usuario ABC");
        newComentario.setFechaPublicacion(new Date());
        newComentario.setFechaModificacion(new Date());
        newComentario.setCalificacion(5.0);

        given()
            .contentType(ContentType.JSON)
            .body(newComentario)
            .when().post("/api/comentarios")
            .then()
            .statusCode(200)
            .body("titulo", equalTo("Excelente Producto"))
            .body("autor", equalTo("Usuario ABC"));
    }

    @Test
    public void testUpdateComentario() {
        Long id = comentario.getId();
        comentario.setTitulo("Servicio Excelente");

        given()
            .contentType(ContentType.JSON)
            .body(comentario)
            .when().put("/api/comentarios/{id}", id)
            .then()
            .statusCode(200)
            .body("titulo", equalTo("Servicio Excelente"));
    }

    @Test
    public void testDeleteComentario() {
        Long id = comentario.getId();

        given()
            .when().delete("/api/comentarios/{id}", id)
            .then()
            .statusCode(204);

        List<Comentario> allComentarios = comentarioRepository.findAll();
        assertEquals(0, allComentarios.size());
    }
}
