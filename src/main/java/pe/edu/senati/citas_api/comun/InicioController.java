package pe.edu.senati.citas_api.comun;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class InicioController {

    private final Environment env;

    public InicioController(Environment env) {
        this.env = env;
    }

    @GetMapping("/")
    public Map<String, Object> inicio() {
        Map<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("proyecto", "citas-api");
        respuesta.put("perfil", String.join(",", env.getActiveProfiles()));
        respuesta.put("estado", "OK");
        return respuesta;
    }
}
