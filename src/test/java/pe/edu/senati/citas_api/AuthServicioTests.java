package pe.edu.senati.citas_api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.server.ResponseStatusException;
import pe.edu.senati.citas_api.auth.AuthService;
import pe.edu.senati.citas_api.auth.LoginDTO;
import pe.edu.senati.citas_api.auth.SesionDTO;
import pe.edu.senati.citas_api.servicios.ServicioDTO;
import pe.edu.senati.citas_api.servicios.ServicioService;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("demo")
class AuthServicioTests {

    @Autowired
    private AuthService authService;

    @Autowired
    private ServicioService servicioService;

    @Test
    void testLoginValidoGerente() {
        LoginDTO login = new LoginDTO("gerente@terapias.pe", "Clave123");
        SesionDTO sesion = authService.login(login);
        assertNotNull(sesion);
        assertEquals("GERENTE", sesion.getRol());
    }

    @Test
    void testLoginClaveInvalida() {
        LoginDTO login = new LoginDTO("gerente@terapias.pe", "ClaveErronea");
        assertThrows(ResponseStatusException.class, () -> authService.login(login));
    }

    @Test
    void testLoginUsuarioInactivo() {
        LoginDTO login = new LoginDTO("diego.ramirez@terapias.pe", "Clave123");
        assertThrows(ResponseStatusException.class, () -> authService.login(login));
    }

    @Test
    void testLoginRolNoPermitido() {
        LoginDTO login = new LoginDTO("maria.gutierrez@terapias.pe", "Clave123");
        assertThrows(ResponseStatusException.class, () -> authService.login(login));
    }

    @Test
    void testServicioOperacionesBasicas() {
        List<ServicioDTO> activos = servicioService.listarActivos();
        assertFalse(activos.isEmpty());

        ServicioDTO nuevo = new ServicioDTO(null, "Servicio Unico", "Desc", new BigDecimal("75.00"), 45, null, true);
        ServicioDTO creado = servicioService.crear(nuevo);
        assertNotNull(creado.getId());

        assertThrows(ResponseStatusException.class, () -> servicioService.crear(nuevo));
        assertThrows(ResponseStatusException.class, () -> servicioService.eliminar(1L));
    }
}
