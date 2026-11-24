package pe.edu.senati.citas_api.servicios;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class ServicioDTO {

    private Long id;

    @NotBlank(message = "El título es obligatorio")
    @Size(max = 80, message = "El título no puede exceder 80 caracteres")
    private String titulo;

    @Size(max = 255, message = "La descripción no puede exceder 255 caracteres")
    private String descripcion;

    @NotNull(message = "El costo es obligatorio")
    @DecimalMin(value = "0.0", message = "El costo no puede ser negativo")
    private BigDecimal costo;

    @NotNull(message = "La duración es obligatoria")
    @Min(value = 15, message = "La duración mínima es de 15 minutos")
    @Max(value = 180, message = "La duración máxima es de 180 minutos")
    private Integer duracion;

    private String foto;

    private Boolean activo = true;

    public ServicioDTO() {
    }

    public ServicioDTO(Long id, String titulo, String descripcion, BigDecimal costo, Integer duracion, String foto, Boolean activo) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.costo = costo;
        this.duracion = duracion;
        this.foto = foto;
        this.activo = activo;
    }

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

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
