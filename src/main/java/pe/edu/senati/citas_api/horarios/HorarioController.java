package pe.edu.senati.citas_api.horarios;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/horarios")
public class HorarioController {

    private final HorarioService horarioService;

    public HorarioController(HorarioService horarioService) {
        this.horarioService = horarioService;
    }

    @GetMapping
    public ResponseEntity<List<HorarioDTO>> listar() {
        return ResponseEntity.ok(horarioService.listar());
    }

    @GetMapping("/terapista/{terapistaId}")
    public ResponseEntity<List<HorarioDTO>> listarPorTerapista(@PathVariable Long terapistaId) {
        return ResponseEntity.ok(horarioService.listarPorTerapista(terapistaId));
    }

    @GetMapping("/dia/{dia}")
    public ResponseEntity<List<HorarioDTO>> listarPorDia(@PathVariable Integer dia) {
        return ResponseEntity.ok(horarioService.listarPorDia(dia));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HorarioDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(horarioService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<HorarioDTO> crear(@Valid @RequestBody HorarioDTO dto) {
        HorarioDTO creado = horarioService.crear(dto);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HorarioDTO> actualizar(@PathVariable Long id, @Valid @RequestBody HorarioDTO dto) {
        HorarioDTO actualizado = horarioService.actualizar(id, dto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        horarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
