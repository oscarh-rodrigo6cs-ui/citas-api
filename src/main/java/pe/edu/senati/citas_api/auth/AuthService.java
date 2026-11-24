package pe.edu.senati.citas_api.auth;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;

    public AuthService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public SesionDTO login(LoginDTO dto) {
        if (dto.getEmail() == null || dto.getEmail().trim().isEmpty() ||
            dto.getPassword() == null || dto.getPassword().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email y contraseña son obligatorios");
        }

        Usuario usuario = usuarioRepository.findByEmail(dto.getEmail().trim())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales incorrectas"));

        if (!usuario.getPassword().equals(dto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales incorrectas");
        }

        if (Boolean.FALSE.equals(usuario.getActivo())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Usuario inactivo");
        }

        String rolNombre = usuario.getRol() != null ? usuario.getRol().getNombre() : "";
        if (!"GERENTE".equalsIgnoreCase(rolNombre) && !"ADMIN".equalsIgnoreCase(rolNombre)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acceso denegado: solo personal autorizado (GERENTE o ADMIN)");
        }

        return new SesionDTO(usuario.getId(), usuario.getEmail(), rolNombre, "Inicio de sesión exitoso");
    }
}
