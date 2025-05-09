package labrini.ouiam.gestiondeshopitauxbackendv1.MAPPERS;

import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.PrescriptionMedicalDTO;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.PrescriptionMedical;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionMedicalMapper {
    public PrescriptionMedicalDTO toDto(PrescriptionMedical entity) {
        PrescriptionMedicalDTO dto = new PrescriptionMedicalDTO();
        BeanUtils.copyProperties(entity, dto);
        dto.setId(entity.getIdPrescriptionMedical());
        if (entity.getDossierMedical() != null) {
            dto.setDossierMedicalId(entity.getDossierMedical().getIdDossierMedical());
        }
        return dto;
    }

    public PrescriptionMedical toEntity(PrescriptionMedicalDTO dto) {
        PrescriptionMedical entity = new PrescriptionMedical();
        BeanUtils.copyProperties(dto, entity);
        entity.setIdPrescriptionMedical(dto.getId());
        return entity;
    }
}