package pe.edu.senati.citas_api.horarios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {
    List<Horario> findByTerapistaId(Long terapistaId);
    List<Horario> findByDiaSemana(Integer diaSemana);
    boolean existsByTerapistaIdAndDiaSemana(Long terapistaId, Integer diaSemana);
    boolean existsByTerapistaIdAndDiaSemanaAndIdNot(Long terapistaId, Integer diaSemana, Long id);
}
