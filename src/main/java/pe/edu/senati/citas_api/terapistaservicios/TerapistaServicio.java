package pe.edu.senati.citas_api.terapistaservicios;

import jakarta.persistence.*;
import pe.edu.senati.citas_api.servicios.Servicio;
import pe.edu.senati.citas_api.terapistas.Terapista;

@Entity
@Table(name = "terapista_servicios")
public class TerapistaServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "terapista_id", nullable = false)
    private Terapista terapista;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "servicio_id", nullable = false)
    private Servicio servicio;

    public TerapistaServicio() {
    }

    public TerapistaServicio(Long id, Terapista terapista, Servicio servicio) {
        this.id = id;
        this.terapista = terapista;
        this.servicio = servicio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Terapista getTerapista() {
        return terapista;
    }

    public void setTerapista(Terapista terapista) {
        this.terapista = terapista;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }
}
