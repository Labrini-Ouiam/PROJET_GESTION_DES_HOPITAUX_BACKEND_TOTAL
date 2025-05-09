package gestiondeshopitauxbackend.MAPPERS;

import gestiondeshopitauxbackend.DTOS.ResultatExamenDTO;
import gestiondeshopitauxbackend.ENTITIES.ResultatExamen;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ResultatExamenMapper {
    public ResultatExamenDTO toDto(ResultatExamen entity) {
        ResultatExamenDTO dto = new ResultatExamenDTO();
        BeanUtils.copyProperties(entity, dto);
        dto.setId(entity.getIdResultatExamen());
        if (entity.getDossierMedical() != null) {
            dto.setDossierMedicalId(entity.getDossierMedical().getIdDossierMedical());
            dto.setDossierMedicalDescription(entity.getDossierMedical().getDescription());
        }
        dto.setDateLastAction(entity.getDateLastAction());
        return dto;
    }

    public ResultatExamen toEntity(ResultatExamenDTO dto) {
        ResultatExamen entity = new ResultatExamen();
        BeanUtils.copyProperties(dto, entity);
        entity.setIdResultatExamen(dto.getId());
        return entity;
    }
}