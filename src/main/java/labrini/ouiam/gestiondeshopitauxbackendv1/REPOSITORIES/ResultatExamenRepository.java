package labrini.ouiam.gestiondeshopitauxbackendv1.REPOSITORIES;

import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.ResultatExamen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultatExamenRepository extends JpaRepository<ResultatExamen, Long> {
    boolean existsByDossierMedical_IdDossierMedicalAndDescriptionContainingIgnoreCase(Long dossierId, String description);

    List<ResultatExamen> findByDossierMedical_IdDossierMedical(Long dossierId);
}