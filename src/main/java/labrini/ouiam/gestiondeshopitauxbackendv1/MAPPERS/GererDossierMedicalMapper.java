package labrini.ouiam.gestiondeshopitauxbackendv1.MAPPERS;

import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.GererDossierMedicalDTO;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.GererDossierMedical;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class GererDossierMedicalMapper {
    public GererDossierMedicalDTO toDto(GererDossierMedical entity) {
        GererDossierMedicalDTO dto = new GererDossierMedicalDTO();
        BeanUtils.copyProperties(entity, dto);
        dto.setId(entity.getIdGererDossierMedical());
        if (entity.getDossierMedical() != null) {
            dto.setDossierMedicalId(entity.getDossierMedical().getIdDossierMedical());
        }
        if (entity.getUtilisateur() != null) {
            dto.setUtilisateurId(entity.getUtilisateur().getIdUtilisateur());
        }
        return dto;
    }

    public GererDossierMedical toEntity(GererDossierMedicalDTO dto) {
        GererDossierMedical entity = new GererDossierMedical();
        BeanUtils.copyProperties(dto, entity);
        entity.setIdGererDossierMedical(dto.getId());
        return entity;
    }
}