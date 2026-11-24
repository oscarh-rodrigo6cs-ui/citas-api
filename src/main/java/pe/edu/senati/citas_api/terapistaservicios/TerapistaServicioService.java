package pe.edu.senati.citas_api.terapistaservicios;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pe.edu.senati.citas_api.servicios.Servicio;
import pe.edu.senati.citas_api.servicios.ServicioRepository;
import pe.edu.senati.citas_api.terapistas.Terapista;
import pe.edu.senati.citas_api.terapistas.TerapistaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TerapistaServicioService {

    private final TerapistaServicioRepository terapistaServicioRepository;
    private final TerapistaRepository terapistaRepository;
    private final ServicioRepository servicioRepository;

    public TerapistaServicioService(TerapistaServicioRepository terapistaServicioRepository,
                                  TerapistaRepository terapistaRepository,
                                  ServicioRepository servicioRepository) {
        this.terapistaServicioRepository = terapistaServicioRepository;
        this.terapistaRepository = terapistaRepository;
        this.servicioRepository = servicioRepository;
    }

    public List<TerapistaServicioDTO> listar() {
        return terapistaServicioRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<TerapistaServicioDTO> listarPorTerapista(Long terapistaId) {
        return terapistaServicioRepository.findByTerapistaId(terapistaId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<TerapistaServicioDTO> listarPorServicio(Long servicioId) {
        return terapistaServicioRepository.findByServicioId(servicioId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public TerapistaServicioDTO buscarPorId(Long id) {
        TerapistaServicio ts = terapistaServicioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Asociación no encontrada con ID: " + id));
        return toDTO(ts);
    }

    @Transactional
    public TerapistaServicioDTO crear(TerapistaServicioDTO dto) {
        Terapista terapista = terapistaRepository.findById(dto.getTerapistaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "El terapista especificado no existe con ID: " + dto.getTerapistaId()));

        Servicio servicio = servicioRepository.findById(dto.getServicioId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "El servicio especificado no existe con ID: " + dto.getServicioId()));

        if (terapistaServicioRepository.existsByTerapistaIdAndServicioId(dto.getTerapistaId(), dto.getServicioId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El terapista ya tiene asignado este servicio");
        }

        TerapistaServicio ts = new TerapistaServicio();
        ts.setTerapista(terapista);
        ts.setServicio(servicio);

        return toDTO(terapistaServicioRepository.save(ts));
    }

    @Transactional
    public void eliminar(Long id) {
        if (!terapistaServicioRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Asociación no encontrada con ID: " + id);
        }
        terapistaServicioRepository.deleteById(id);
    }

    private TerapistaServicioDTO toDTO(TerapistaServicio ts) {
        String nombreTerapista = ts.getTerapista() != null
                ? ts.getTerapista().getNombres() + " " + ts.getTerapista().getApellidos()
                : null;
        String tituloServicio = ts.getServicio() != null
                ? ts.getServicio().getTitulo()
                : null;

        return new TerapistaServicioDTO(
                ts.getId(),
                ts.getTerapista() != null ? ts.getTerapista().getId() : null,
                nombreTerapista,
                ts.getServicio() != null ? ts.getServicio().getId() : null,
                tituloServicio
        );
    }
}
