package labrini.ouiam.gestiondeshopitauxbackendv1.SERVICES;

import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.PrescriptionMedicamentDetailDTO;
import java.util.List;

public interface PrescriptionMedicamentDetailService {
    PrescriptionMedicamentDetailDTO createDetail(PrescriptionMedicamentDetailDTO detailDTO);
    PrescriptionMedicamentDetailDTO getDetailById(Long id);
    List<PrescriptionMedicamentDetailDTO> getDetailsByPrescription(Long prescriptionId);
    PrescriptionMedicamentDetailDTO updateDetail(Long id, PrescriptionMedicamentDetailDTO detailDTO);
    void deleteDetail(Long id);
}