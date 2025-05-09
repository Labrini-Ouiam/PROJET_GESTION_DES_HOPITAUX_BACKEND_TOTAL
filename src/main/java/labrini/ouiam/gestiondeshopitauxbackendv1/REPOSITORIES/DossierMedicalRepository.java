package labrini.ouiam.gestiondeshopitauxbackendv1.REPOSITORIES;

import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.DossierMedical;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DossierMedicalRepository extends JpaRepository<DossierMedical, Long> {
    List<DossierMedical> findByPatient_IdUtilisateur(Long patientId);
    List<DossierMedical>  findByDescriptionContainingIgnoreCase(String description);
}