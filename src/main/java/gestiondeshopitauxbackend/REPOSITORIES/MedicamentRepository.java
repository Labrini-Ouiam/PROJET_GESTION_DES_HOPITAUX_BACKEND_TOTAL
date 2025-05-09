package gestiondeshopitauxbackend.REPOSITORIES;

import gestiondeshopitauxbackend.ENTITIES.Medicament;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MedicamentRepository extends JpaRepository<Medicament, Long> {
    List<Medicament> findByNomContainingIgnoreCase(String nom);
}