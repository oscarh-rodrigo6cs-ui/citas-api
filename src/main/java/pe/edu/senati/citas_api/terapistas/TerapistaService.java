package pe.edu.senati.citas_api.terapistas;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pe.edu.senati.citas_api.auth.Usuario;
import pe.edu.senati.citas_api.auth.UsuarioRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TerapistaService {

    private final TerapistaRepository terapistaRepository;
    private final UsuarioRepository usuarioRepository;
    private final JdbcTemplate jdbcTemplate;

    public TerapistaService(TerapistaRepository terapistaRepository,
                            UsuarioRepository usuarioRepository,
                            JdbcTemplate jdbcTemplate) {
        this.terapistaRepository = terapistaRepository;
        this.usuarioRepository = usuarioRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<TerapistaDTO> listar() {
        return terapistaRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<TerapistaDTO> listarActivos() {
        return terapistaRepository.findByActivoTrue().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public TerapistaDTO buscarPorId(Long id) {
        Terapista t = terapistaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Terapista no encontrado con ID: " + id));
        return toDTO(t);
    }

    @Transactional
    public TerapistaDTO crear(TerapistaDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "El usuario especificado no existe con ID: " + dto.getUsuarioId()));

        if (usuario.getRol() == null || !"TERAPISTA".equalsIgnoreCase(usuario.getRol().getNombre())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El usuario debe tener rol TERAPISTA para registrarse como personal de terapia");
        }

        if (terapistaRepository.existsByUsuarioId(dto.getUsuarioId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un perfil de terapista para este usuario");
        }

        if (terapistaRepository.existsByDni(dto.getDni().trim())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un terapista registrado con el DNI: " + dto.getDni());
        }

        Terapista t = new Terapista();
        t.setUsuario(usuario);
        t.setNombres(dto.getNombres().trim());
        t.setApellidos(dto.getApellidos().trim());
        t.setDni(dto.getDni().trim());
        t.setTelefono(dto.getTelefono() != null ? dto.getTelefono().trim() : null);
        t.setActivo(dto.getActivo() != null ? dto.getActivo() : true);

        return toDTO(terapistaRepository.save(t));
    }

    @Transactional
    public TerapistaDTO actualizar(Long id, TerapistaDTO dto) {
        Terapista t = terapistaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Terapista no encontrado con ID: " + id));

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "El usuario especificado no existe con ID: " + dto.getUsuarioId()));

        if (usuario.getRol() == null || !"TERAPISTA".equalsIgnoreCase(usuario.getRol().getNombre())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El usuario debe tener rol TERAPISTA");
        }

        if (terapistaRepository.existsByUsuarioIdAndIdNot(dto.getUsuarioId(), id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El usuario indicado ya está asignado a otro terapista");
        }

        if (terapistaRepository.existsByDniAndIdNot(dto.getDni().trim(), id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El DNI indicado ya está asignado a otro terapista");
        }

        t.setUsuario(usuario);
        t.setNombres(dto.getNombres().trim());
        t.setApellidos(dto.getApellidos().trim());
        t.setDni(dto.getDni().trim());
        t.setTelefono(dto.getTelefono() != null ? dto.getTelefono().trim() : null);
        if (dto.getActivo() != null) {
            t.setActivo(dto.getActivo());
        }

        return toDTO(terapistaRepository.save(t));
    }

    @Transactional
    public void eliminar(Long id) {
        if (!terapistaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Terapista no encontrado con ID: " + id);
        }

        Integer countCitas = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM citas WHERE terapista_id = ?", Integer.class, id);
        Integer countHorarios = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM horarios WHERE terapista_id = ?", Integer.class, id);
        Integer countServicios = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM terapista_servicios WHERE terapista_id = ?", Integer.class, id);

        if ((countCitas != null && countCitas > 0) ||
            (countHorarios != null && countHorarios > 0) ||
            (countServicios != null && countServicios > 0)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "No se puede eliminar el terapista porque tiene horarios, citas o servicios asignados");
        }

        terapistaRepository.deleteById(id);
    }

    private TerapistaDTO toDTO(Terapista t) {
        return new TerapistaDTO(
                t.getId(),
                t.getUsuario() != null ? t.getUsuario().getId() : null,
                t.getUsuario() != null ? t.getUsuario().getEmail() : null,
                t.getNombres(),
                t.getApellidos(),
                t.getDni(),
                t.getTelefono(),
                t.getActivo()
        );
    }
}
