package pe.edu.senati.citas_api.servicios;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicioService {

    private final ServicioRepository servicioRepository;
    private final JdbcTemplate jdbcTemplate;

    public ServicioService(ServicioRepository servicioRepository, JdbcTemplate jdbcTemplate) {
        this.servicioRepository = servicioRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ServicioDTO> listar() {
        return servicioRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<ServicioDTO> listarActivos() {
        return servicioRepository.findByActivoTrue().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<ServicioDTO> buscarPorTitulo(String titulo) {
        return servicioRepository.findByTituloContainingIgnoreCase(titulo != null ? titulo.trim() : "").stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ServicioDTO buscarPorId(Long id) {
        Servicio s = servicioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Servicio no encontrado con ID: " + id));
        return toDTO(s);
    }

    @Transactional
    public ServicioDTO crear(ServicioDTO dto) {
        if (servicioRepository.existsByTituloIgnoreCase(dto.getTitulo().trim())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un servicio con el título: " + dto.getTitulo());
        }

        Servicio s = new Servicio();
        s.setTitulo(dto.getTitulo().trim());
        s.setDescripcion(dto.getDescripcion());
        s.setCosto(dto.getCosto());
        s.setDuracion(dto.getDuracion());
        s.setFoto(dto.getFoto());
        s.setActivo(dto.getActivo() != null ? dto.getActivo() : true);

        return toDTO(servicioRepository.save(s));
    }

    @Transactional
    public ServicioDTO actualizar(Long id, ServicioDTO dto) {
        Servicio s = servicioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Servicio no encontrado con ID: " + id));

        if (servicioRepository.existsByTituloIgnoreCaseAndIdNot(dto.getTitulo().trim(), id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe otro servicio con el título: " + dto.getTitulo());
        }

        s.setTitulo(dto.getTitulo().trim());
        s.setDescripcion(dto.getDescripcion());
        s.setCosto(dto.getCosto());
        s.setDuracion(dto.getDuracion());
        s.setFoto(dto.getFoto());
        if (dto.getActivo() != null) {
            s.setActivo(dto.getActivo());
        }

        return toDTO(servicioRepository.save(s));
    }

    @Transactional
    public void eliminar(Long id) {
        if (!servicioRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Servicio no encontrado con ID: " + id);
        }

        Integer countCitas = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM citas WHERE servicio_id = ?", Integer.class, id);
        Integer countTerapistas = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM terapista_servicios WHERE servicio_id = ?", Integer.class, id);
        Integer countConsultas = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM consulta_servicios WHERE servicio_id = ?", Integer.class, id);

        if ((countCitas != null && countCitas > 0) ||
            (countTerapistas != null && countTerapistas > 0) ||
            (countConsultas != null && countConsultas > 0)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "No se puede eliminar el servicio porque se encuentra asignado o en uso");
        }

        servicioRepository.deleteById(id);
    }

    private ServicioDTO toDTO(Servicio s) {
        return new ServicioDTO(
                s.getId(),
                s.getTitulo(),
                s.getDescripcion(),
                s.getCosto(),
                s.getDuracion(),
                s.getFoto(),
                s.getActivo()
        );
    }
}
