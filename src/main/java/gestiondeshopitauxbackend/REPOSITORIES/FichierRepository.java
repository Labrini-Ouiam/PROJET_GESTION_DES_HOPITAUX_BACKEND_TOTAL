package gestiondeshopitauxbackend.REPOSITORIES;

import gestiondeshopitauxbackend.ENTITIES.Fichier;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface FichierRepository extends JpaRepository<Fichier, Long> {
    List<Fichier> findByDossierMedical_IdDossierMedical(Long dossierMedicalId);
    Optional<Fichier> findByNomAndDossierMedical_IdDossierMedical(String nom, Long dossierMedicalId);
}