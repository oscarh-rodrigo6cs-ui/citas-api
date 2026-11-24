package pe.edu.senati.citas_api.terapistaservicios;

import jakarta.validation.constraints.NotNull;

public class TerapistaServicioDTO {

    private Long id;

    @NotNull(message = "El terapistaId es obligatorio")
    private Long terapistaId;

    private String terapistaNombre;

    @NotNull(message = "El servicioId es obligatorio")
    private Long servicioId;

    private String servicioTitulo;

    public TerapistaServicioDTO() {
    }

    public TerapistaServicioDTO(Long id, Long terapistaId, String terapistaNombre, Long servicioId, String servicioTitulo) {
        this.id = id;
        this.terapistaId = terapistaId;
        this.terapistaNombre = terapistaNombre;
        this.servicioId = servicioId;
        this.servicioTitulo = servicioTitulo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTerapistaId() {
        return terapistaId;
    }

    public void setTerapistaId(Long terapistaId) {
        this.terapistaId = terapistaId;
    }

    public String getTerapistaNombre() {
        return terapistaNombre;
    }

    public void setTerapistaNombre(String terapistaNombre) {
        this.terapistaNombre = terapistaNombre;
    }

    public Long getServicioId() {
        return servicioId;
    }

    public void setServicioId(Long servicioId) {
        this.servicioId = servicioId;
    }

    public String getServicioTitulo() {
        return servicioTitulo;
    }

    public void setServicioTitulo(String servicioTitulo) {
        this.servicioTitulo = servicioTitulo;
    }
}
