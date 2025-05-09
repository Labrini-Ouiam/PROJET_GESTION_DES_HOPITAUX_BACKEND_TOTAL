package labrini.ouiam.gestiondeshopitauxbackendv1.MAPPERS;

import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.PrescriptionMedicamentDetailDTO;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.PrescriptionMedicamentDetail;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionMedicamentDetailMapper {
    public PrescriptionMedicamentDetailDTO toDto(PrescriptionMedicamentDetail entity) {
        PrescriptionMedicamentDetailDTO dto = new PrescriptionMedicamentDetailDTO();
        BeanUtils.copyProperties(entity, dto);
        dto.setId(entity.getIdPrescriptionMedicamentDetail());
        if (entity.getPrescription() != null) {
            dto.setPrescriptionId(entity.getPrescription().getIdPrescriptionMedical());
            dto.setPrescriptionDescription(entity.getPrescription().getDescription());
            dto.setDateLastAction(entity.getPrescription().getDateLastAction());
        }
        if (entity.getMedicament() != null) {
            dto.setMedicamentId(entity.getMedicament().getIdMedicament());
            dto.setMedicamentNom(entity.getMedicament().getNom());
            dto.setMedicamentDescription(entity.getMedicament().getDescription());
        }
        return dto;
    }

    public PrescriptionMedicamentDetail toEntity(PrescriptionMedicamentDetailDTO dto) {
        PrescriptionMedicamentDetail entity = new PrescriptionMedicamentDetail();
        BeanUtils.copyProperties(dto, entity);
        entity.setIdPrescriptionMedicamentDetail(dto.getId());
        return entity;
    }
}