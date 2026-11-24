package pe.edu.senati.citas_api;

import java.util.TimeZone;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CitasApiApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("America/Lima"));
		SpringApplication.run(CitasApiApplication.class, args);
	}

}
