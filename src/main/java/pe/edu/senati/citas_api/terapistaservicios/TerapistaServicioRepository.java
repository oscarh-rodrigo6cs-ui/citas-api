package pe.edu.senati.citas_api.terapistaservicios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TerapistaServicioRepository extends JpaRepository<TerapistaServicio, Long> {
    List<TerapistaServicio> findByTerapistaId(Long terapistaId);
    List<TerapistaServicio> findByServicioId(Long servicioId);
    boolean existsByTerapistaIdAndServicioId(Long terapistaId, Long servicioId);
}
