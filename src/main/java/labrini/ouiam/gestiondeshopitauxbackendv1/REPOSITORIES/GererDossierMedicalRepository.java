package labrini.ouiam.gestiondeshopitauxbackendv1.REPOSITORIES;

import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.GererDossierMedical;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GererDossierMedicalRepository extends JpaRepository<GererDossierMedical, Long> {
    List<GererDossierMedical> findByDossierMedical_IdDossierMedical(Long dossierId);
    List<GererDossierMedical> findByUtilisateur_Id(Long utilisateurId);
    List<GererDossierMedical> findByUtilisateur(Utilisateur utilisateur);
}