package upeu.juliaca.eddy.dtos;

import java.util.Date;

public class ComentarioDto {
    private Long id;
    private String titulo;
    private String descripcion;
    private String autor;
    private Double calificacion;
    private Date fechaPublicacion;
    private Date fechaModificacion;

    // Constructor vacío
    public ComentarioDto() {
    }

    // Constructor con parámetros
    public ComentarioDto(Long id, String titulo, String descripcion, String autor, Double calificacion, Date fechaPublicacion, Date fechaModificacion) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.autor = autor;
        this.calificacion = calificacion;
        this.fechaPublicacion = fechaPublicacion;
        this.fechaModificacion = fechaModificacion;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
}
