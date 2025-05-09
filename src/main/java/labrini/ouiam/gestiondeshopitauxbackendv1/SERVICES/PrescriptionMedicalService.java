package labrini.ouiam.gestiondeshopitauxbackendv1.SERVICES;
import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.PrescriptionMedicalDTO;

import java.util.List;
public interface PrescriptionMedicalService {
    PrescriptionMedicalDTO createPrescription(PrescriptionMedicalDTO prescriptionDTO);
    PrescriptionMedicalDTO getPrescriptionById(Long id);
    List<PrescriptionMedicalDTO> getPrescriptionsByDossier(Long dossierId);
    PrescriptionMedicalDTO updatePrescription(Long id, PrescriptionMedicalDTO prescriptionDTO);
    void deletePrescription(Long id);
}
