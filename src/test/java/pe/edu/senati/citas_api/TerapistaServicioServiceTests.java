package pe.edu.senati.citas_api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.server.ResponseStatusException;
import pe.edu.senati.citas_api.terapistaservicios.TerapistaServicioDTO;
import pe.edu.senati.citas_api.terapistaservicios.TerapistaServicioService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("demo")
class TerapistaServicioServiceTests {

    @Autowired
    private TerapistaServicioService terapistaServicioService;

    @Test
    void testListarAsociaciones() {
        List<TerapistaServicioDTO> todas = terapistaServicioService.listar();
        assertFalse(todas.isEmpty());

        List<TerapistaServicioDTO> porTerapista = terapistaServicioService.listarPorTerapista(1L);
        assertFalse(porTerapista.isEmpty());

        List<TerapistaServicioDTO> porServicio = terapistaServicioService.listarPorServicio(1L);
        assertFalse(porServicio.isEmpty());
    }

    @Test
    void testCrearAsociacionDuplicada() {
        // Terapista 1 already has servicio 1 in test data
        TerapistaServicioDTO duplicado = new TerapistaServicioDTO(null, 1L, null, 1L, null);
        assertThrows(ResponseStatusException.class, () -> terapistaServicioService.crear(duplicado));
    }

    @Test
    void testCrearAsociacionEntidadInexistente() {
        TerapistaServicioDTO terapistaInvalido = new TerapistaServicioDTO(null, 9999L, null, 1L, null);
        assertThrows(ResponseStatusException.class, () -> terapistaServicioService.crear(terapistaInvalido));

        TerapistaServicioDTO servicioInvalido = new TerapistaServicioDTO(null, 1L, null, 9999L, null);
        assertThrows(ResponseStatusException.class, () -> terapistaServicioService.crear(servicioInvalido));
    }

    @Test
    void testCrearYEliminarAsociacion() {
        // Terapista 2 does not have servicio 1 in initial data
        TerapistaServicioDTO nuevo = new TerapistaServicioDTO(null, 2L, null, 1L, null);
        TerapistaServicioDTO creado = terapistaServicioService.crear(nuevo);
        assertNotNull(creado.getId());
        assertEquals("Carlos Paredes Ríos", creado.getTerapistaNombre());
        assertEquals("Fisioterapia", creado.getServicioTitulo());

        terapistaServicioService.eliminar(creado.getId());
        assertThrows(ResponseStatusException.class, () -> terapistaServicioService.buscarPorId(creado.getId()));
    }
}
