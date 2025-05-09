package labrini.ouiam.gestiondeshopitauxbackendv1.REPOSITORIES;

import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.GererDossierMedical;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GererDossierMedicalRepository extends JpaRepository<GererDossierMedical, Long> {
    List<GererDossierMedical> findByDossierMedical_IdDossierMedical(Long dossierId);
    List<GererDossierMedical> findByUtilisateur_IdUtilisateur(Long utilisateurId);
}