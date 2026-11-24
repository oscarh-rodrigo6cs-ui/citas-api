package pe.edu.senati.citas_api.horarios;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public class HorarioDTO {

    private Long id;

    @NotNull(message = "El terapistaId es obligatorio")
    private Long terapistaId;

    private String terapistaNombre;

    @NotNull(message = "El día de la semana es obligatorio")
    @Min(value = 1, message = "El día debe estar entre 1 (lunes) y 7 (domingo)")
    @Max(value = 7, message = "El día debe estar entre 1 (lunes) y 7 (domingo)")
    private Integer diaSemana;

    @NotNull(message = "La hora de inicio es obligatoria")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime horaInicio;

    @NotNull(message = "La hora de fin es obligatoria")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime horaFin;

    public HorarioDTO() {
    }

    public HorarioDTO(Long id, Long terapistaId, String terapistaNombre, Integer diaSemana, LocalTime horaInicio, LocalTime horaFin) {
        this.id = id;
        this.terapistaId = terapistaId;
        this.terapistaNombre = terapistaNombre;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
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

    public Integer getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(Integer diaSemana) {
        this.diaSemana = diaSemana;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }
}
