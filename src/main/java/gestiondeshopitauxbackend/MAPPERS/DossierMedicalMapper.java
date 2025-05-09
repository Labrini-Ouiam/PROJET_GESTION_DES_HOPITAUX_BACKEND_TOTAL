package gestiondeshopitauxbackend.MAPPERS;

import gestiondeshopitauxbackend.DTOS.DossierMedicalDTO;
import gestiondeshopitauxbackend.ENTITIES.DossierMedical;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class DossierMedicalMapper {
    public DossierMedicalDTO toDto(DossierMedical entity) {
        DossierMedicalDTO dto = new DossierMedicalDTO();
        BeanUtils.copyProperties(entity, dto);
        dto.setId(entity.getIdDossierMedical());
        if (entity.getPatient() != null) {
            dto.setPatientId(entity.getPatient().getIdUtilisateur());
            dto.setPatientNom(entity.getPatient().getNom());
            dto.setPatientPrenom(entity.getPatient().getPrenom());
        }
        return dto;
    }

    public DossierMedical toEntity(DossierMedicalDTO dto) {
        DossierMedical entity = new DossierMedical();
        BeanUtils.copyProperties(dto, entity);
        entity.setIdDossierMedical(dto.getId());
        return entity;
    }
}