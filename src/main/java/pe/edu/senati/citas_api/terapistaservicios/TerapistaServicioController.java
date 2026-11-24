package pe.edu.senati.citas_api.terapistaservicios;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/terapista-servicios")
public class TerapistaServicioController {

    private final TerapistaServicioService terapistaServicioService;

    public TerapistaServicioController(TerapistaServicioService terapistaServicioService) {
        this.terapistaServicioService = terapistaServicioService;
    }

    @GetMapping
    public ResponseEntity<List<TerapistaServicioDTO>> listar() {
        return ResponseEntity.ok(terapistaServicioService.listar());
    }

    @GetMapping("/terapista/{terapistaId}")
    public ResponseEntity<List<TerapistaServicioDTO>> listarPorTerapista(@PathVariable Long terapistaId) {
        return ResponseEntity.ok(terapistaServicioService.listarPorTerapista(terapistaId));
    }

    @GetMapping("/servicio/{servicioId}")
    public ResponseEntity<List<TerapistaServicioDTO>> listarPorServicio(@PathVariable Long servicioId) {
        return ResponseEntity.ok(terapistaServicioService.listarPorServicio(servicioId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TerapistaServicioDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(terapistaServicioService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<TerapistaServicioDTO> crear(@Valid @RequestBody TerapistaServicioDTO dto) {
        TerapistaServicioDTO creado = terapistaServicioService.crear(dto);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        terapistaServicioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
