package gestiondeshopitauxbackend.REPOSITORIES;

import gestiondeshopitauxbackend.ENTITIES.PrescriptionMedical;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PrescriptionMedicalRepository extends JpaRepository<PrescriptionMedical, Long> {
    List<PrescriptionMedical> findByDossierMedical_IdDossierMedical(Long dossierId);
}