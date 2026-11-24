package pe.edu.senati.citas_api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.server.ResponseStatusException;
import pe.edu.senati.citas_api.terapistas.TerapistaDTO;
import pe.edu.senati.citas_api.terapistas.TerapistaService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("demo")
class TerapistaServiceTests {

    @Autowired
    private TerapistaService terapistaService;

    @Test
    void testListarTerapistas() {
        List<TerapistaDTO> lista = terapistaService.listar();
        assertFalse(lista.isEmpty());

        List<TerapistaDTO> activos = terapistaService.listarActivos();
        assertFalse(activos.isEmpty());
    }

    @Test
    void testCrearTerapistaRolInvalido() {
        // usuario id 1 is GERENTE, not TERAPISTA
        TerapistaDTO nuevo = new TerapistaDTO(null, 1L, null, "Juan", "Perez", "88887777", "987654321", true);
        assertThrows(ResponseStatusException.class, () -> terapistaService.crear(nuevo));
    }

    @Test
    void testCrearTerapistaExitosoYDuplicado() {
        // usuario id 23 is nuevo.terapista@terapias.pe with rol TERAPISTA and no terapista profile yet
        TerapistaDTO nuevo = new TerapistaDTO(null, 23L, null, "Carlos", "Nuevo", "78912345", "912345678", true);
        TerapistaDTO creado = terapistaService.crear(nuevo);
        assertNotNull(creado.getId());

        // Repeated usuario
        TerapistaDTO duplicadoUsuario = new TerapistaDTO(null, 23L, null, "Otro", "Nombre", "11223344", "999888777", true);
        assertThrows(ResponseStatusException.class, () -> terapistaService.crear(duplicadoUsuario));

        // Repeated DNI
        TerapistaDTO duplicadoDni = new TerapistaDTO(null, 4L, null, "Otro", "Nombre", "78912345", "999888777", true);
        assertThrows(ResponseStatusException.class, () -> terapistaService.crear(duplicadoDni));
    }

    @Test
    void testEliminarTerapistaEnUso() {
        // Terapista 1 has horarios, citas, etc.
        assertThrows(ResponseStatusException.class, () -> terapistaService.eliminar(1L));
    }
}
