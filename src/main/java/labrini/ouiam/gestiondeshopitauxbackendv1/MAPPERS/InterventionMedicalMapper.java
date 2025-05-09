package labrini.ouiam.gestiondeshopitauxbackendv1.MAPPERS;

import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.InterventionMedicalDTO;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.InterventionMedical;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class InterventionMedicalMapper {
    public InterventionMedicalDTO toDto(InterventionMedical entity) {
        InterventionMedicalDTO dto = new InterventionMedicalDTO();
        BeanUtils.copyProperties(entity, dto);
        dto.setId(entity.getIdInterventionMedical());
        if (entity.getDossierMedical() != null) {
            dto.setDossierMedicalId(entity.getDossierMedical().getIdDossierMedical());
            if (entity.getDossierMedical().getPatient() != null) {
                dto.setPatientNom(entity.getDossierMedical().getPatient().getNom());
                dto.setPatientPrenom(entity.getDossierMedical().getPatient().getPrenom());
            }
        }
        dto.setDateIntervention(entity.getDate());
        return dto;
    }

    public InterventionMedical toEntity(InterventionMedicalDTO dto) {
        InterventionMedical entity = new InterventionMedical();
        BeanUtils.copyProperties(dto, entity);
        entity.setIdInterventionMedical(dto.getId());
        return entity;
    }
}