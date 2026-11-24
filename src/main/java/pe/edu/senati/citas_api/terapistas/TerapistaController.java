package pe.edu.senati.citas_api.terapistas;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/terapistas")
public class TerapistaController {

    private final TerapistaService terapistaService;

    public TerapistaController(TerapistaService terapistaService) {
        this.terapistaService = terapistaService;
    }

    @GetMapping
    public ResponseEntity<List<TerapistaDTO>> listar() {
        return ResponseEntity.ok(terapistaService.listar());
    }

    @GetMapping("/activos")
    public ResponseEntity<List<TerapistaDTO>> listarActivos() {
        return ResponseEntity.ok(terapistaService.listarActivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TerapistaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(terapistaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<TerapistaDTO> crear(@Valid @RequestBody TerapistaDTO dto) {
        TerapistaDTO creado = terapistaService.crear(dto);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TerapistaDTO> actualizar(@PathVariable Long id, @Valid @RequestBody TerapistaDTO dto) {
        TerapistaDTO actualizado = terapistaService.actualizar(id, dto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        terapistaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
