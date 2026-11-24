package pe.edu.senati.citas_api.horarios;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pe.edu.senati.citas_api.terapistas.Terapista;
import pe.edu.senati.citas_api.terapistas.TerapistaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HorarioService {

    private final HorarioRepository horarioRepository;
    private final TerapistaRepository terapistaRepository;

    public HorarioService(HorarioRepository horarioRepository, TerapistaRepository terapistaRepository) {
        this.horarioRepository = horarioRepository;
        this.terapistaRepository = terapistaRepository;
    }

    public List<HorarioDTO> listar() {
        return horarioRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<HorarioDTO> listarPorTerapista(Long terapistaId) {
        return horarioRepository.findByTerapistaId(terapistaId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<HorarioDTO> listarPorDia(Integer dia) {
        if (dia < 1 || dia > 7) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El día debe estar entre 1 (lunes) y 7 (domingo)");
        }
        return horarioRepository.findByDiaSemana(dia).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public HorarioDTO buscarPorId(Long id) {
        Horario h = horarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Horario no encontrado con ID: " + id));
        return toDTO(h);
    }

    @Transactional
    public HorarioDTO crear(HorarioDTO dto) {
        Terapista terapista = terapistaRepository.findById(dto.getTerapistaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "El terapista especificado no existe con ID: " + dto.getTerapistaId()));

        if (!dto.getHoraInicio().isBefore(dto.getHoraFin())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La hora de inicio debe ser anterior a la hora de fin");
        }

        if (horarioRepository.existsByTerapistaIdAndDiaSemana(dto.getTerapistaId(), dto.getDiaSemana())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un horario configurado para este terapista en el día " + dto.getDiaSemana());
        }

        Horario h = new Horario();
        h.setTerapista(terapista);
        h.setDiaSemana(dto.getDiaSemana());
        h.setHoraInicio(dto.getHoraInicio());
        h.setHoraFin(dto.getHoraFin());

        return toDTO(horarioRepository.save(h));
    }

    @Transactional
    public HorarioDTO actualizar(Long id, HorarioDTO dto) {
        Horario h = horarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Horario no encontrado con ID: " + id));

        Terapista terapista = terapistaRepository.findById(dto.getTerapistaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "El terapista especificado no existe con ID: " + dto.getTerapistaId()));

        if (!dto.getHoraInicio().isBefore(dto.getHoraFin())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La hora de inicio debe ser anterior a la hora de fin");
        }

        if (horarioRepository.existsByTerapistaIdAndDiaSemanaAndIdNot(dto.getTerapistaId(), dto.getDiaSemana(), id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe otro horario registrado para este terapista en el día " + dto.getDiaSemana());
        }

        h.setTerapista(terapista);
        h.setDiaSemana(dto.getDiaSemana());
        h.setHoraInicio(dto.getHoraInicio());
        h.setHoraFin(dto.getHoraFin());

        return toDTO(horarioRepository.save(h));
    }

    @Transactional
    public void eliminar(Long id) {
        if (!horarioRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Horario no encontrado con ID: " + id);
        }
        horarioRepository.deleteById(id);
    }

    private HorarioDTO toDTO(Horario h) {
        String nombreTerapista = h.getTerapista() != null
                ? h.getTerapista().getNombres() + " " + h.getTerapista().getApellidos()
                : null;
        return new HorarioDTO(
                h.getId(),
                h.getTerapista() != null ? h.getTerapista().getId() : null,
                nombreTerapista,
                h.getDiaSemana(),
                h.getHoraInicio(),
                h.getHoraFin()
        );
    }
}
