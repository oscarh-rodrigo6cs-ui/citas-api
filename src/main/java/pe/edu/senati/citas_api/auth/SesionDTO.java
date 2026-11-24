package pe.edu.senati.citas_api.auth;

public class SesionDTO {

    private Long id;
    private String email;
    private String rol;
    private String mensaje;

    public SesionDTO() {
    }

    public SesionDTO(Long id, String email, String rol, String mensaje) {
        this.id = id;
        this.email = email;
        this.rol = rol;
        this.mensaje = mensaje;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
