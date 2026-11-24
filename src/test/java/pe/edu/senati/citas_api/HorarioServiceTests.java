package pe.edu.senati.citas_api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.server.ResponseStatusException;
import pe.edu.senati.citas_api.horarios.HorarioDTO;
import pe.edu.senati.citas_api.horarios.HorarioService;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("demo")
class HorarioServiceTests {

    @Autowired
    private HorarioService horarioService;

    @Test
    void testListarHorarios() {
        List<HorarioDTO> todos = horarioService.listar();
        assertFalse(todos.isEmpty());

        List<HorarioDTO> porTerapista = horarioService.listarPorTerapista(1L);
        assertFalse(porTerapista.isEmpty());

        List<HorarioDTO> porDia = horarioService.listarPorDia(1);
        assertFalse(porDia.isEmpty());
    }

    @Test
    void testCrearHorarioHorasInvalidas() {
        // horaInicio after horaFin
        HorarioDTO invalid = new HorarioDTO(null, 6L, null, 1, LocalTime.of(15, 0), LocalTime.of(10, 0));
        assertThrows(ResponseStatusException.class, () -> horarioService.crear(invalid));
    }

    @Test
    void testCrearHorarioDiaDuplicado() {
        // Terapista 1 already has dia 1 (Lunes 08:00 - 14:00)
        HorarioDTO duplicado = new HorarioDTO(null, 1L, null, 1, LocalTime.of(15, 0), LocalTime.of(18, 0));
        assertThrows(ResponseStatusException.class, () -> horarioService.crear(duplicado));
    }

    @Test
    void testCrearHorarioExitoso() {
        // Terapista 6 only has dia 6 in initial data; adding dia 1 should succeed
        HorarioDTO nuevo = new HorarioDTO(null, 6L, null, 1, LocalTime.of(9, 0), LocalTime.of(13, 0));
        HorarioDTO creado = horarioService.crear(nuevo);
        assertNotNull(creado.getId());
        assertEquals(1, creado.getDiaSemana());
    }
}
