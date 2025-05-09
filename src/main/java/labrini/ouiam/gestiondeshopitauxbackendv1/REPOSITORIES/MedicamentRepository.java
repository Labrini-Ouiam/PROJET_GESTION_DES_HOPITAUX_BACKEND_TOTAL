package labrini.ouiam.gestiondeshopitauxbackendv1.REPOSITORIES;

import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.Medicament;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MedicamentRepository extends JpaRepository<Medicament, Long> {
    List<Medicament> findByNomContainingIgnoreCase(String nom);
}