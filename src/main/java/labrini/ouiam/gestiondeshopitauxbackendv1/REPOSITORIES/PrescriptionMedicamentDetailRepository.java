package labrini.ouiam.gestiondeshopitauxbackendv1.REPOSITORIES;

import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.PrescriptionMedicamentDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PrescriptionMedicamentDetailRepository extends JpaRepository<PrescriptionMedicamentDetail, Long> {
    List<PrescriptionMedicamentDetail> findByPrescription_IdPrescriptionMedical(Long prescriptionId);
}