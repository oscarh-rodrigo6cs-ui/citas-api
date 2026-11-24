package pe.edu.senati.citas_api.terapistas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TerapistaRepository extends JpaRepository<Terapista, Long> {
    List<Terapista> findByActivoTrue();
    boolean existsByUsuarioId(Long usuarioId);
    boolean existsByUsuarioIdAndIdNot(Long usuarioId, Long id);
    boolean existsByDni(String dni);
    boolean existsByDniAndIdNot(String dni, Long id);
}
