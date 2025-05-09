package gestiondeshopitauxbackend.MAPPERS;

import gestiondeshopitauxbackend.DTOS.PrescriptionMedicalDTO;
import gestiondeshopitauxbackend.ENTITIES.PrescriptionMedical;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionMapper {

    public PrescriptionMedicalDTO toDto(PrescriptionMedical entity){
        PrescriptionMedicalDTO dto = new PrescriptionMedicalDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public String getPatientFullName(PrescriptionMedical prescription) {
        if (prescription.getDossierMedical() != null && prescription.getDossierMedical().getPatient() != null) {
            return prescription.getDossierMedical().getPatient().getNom() + " " +
                    prescription.getDossierMedical().getPatient().getPrenom();
        }
        return null;
    }

    public PrescriptionMedical toEntity(PrescriptionMedicalDTO prescriptionDTO) {
        PrescriptionMedical prescription = new PrescriptionMedical();
        BeanUtils.copyProperties(prescriptionDTO, prescription);
        return prescription;
    }
}