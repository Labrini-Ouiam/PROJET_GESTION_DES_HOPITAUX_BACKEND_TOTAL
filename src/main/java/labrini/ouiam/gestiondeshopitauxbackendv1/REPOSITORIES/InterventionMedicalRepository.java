package labrini.ouiam.gestiondeshopitauxbackendv1.REPOSITORIES;

import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.DossierMedical;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.InterventionMedical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterventionMedicalRepository extends JpaRepository<InterventionMedical, Long> {
    //Arrays findByDossierMedical(DossierMedical dossierMedical);
    List<InterventionMedical> findByDossierMedical(DossierMedical dossierMedical);
}