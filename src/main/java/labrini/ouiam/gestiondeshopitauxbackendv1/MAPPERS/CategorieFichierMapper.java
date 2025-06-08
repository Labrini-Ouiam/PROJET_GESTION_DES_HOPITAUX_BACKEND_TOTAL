package labrini.ouiam.gestiondeshopitauxbackendv1.MAPPERS;

import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.CategorieFichierDTO;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.CategorieFichier;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CategorieFichierMapper {
    public CategorieFichierDTO toDto(CategorieFichier entity) {
        CategorieFichierDTO dto = new CategorieFichierDTO();
        BeanUtils.copyProperties(entity, dto);
        dto.setIdCategorieFichier(entity.getIdCategorieFichier());
        return dto;
    }

    public CategorieFichier toEntity(CategorieFichierDTO dto) {
        CategorieFichier entity = new CategorieFichier();
        BeanUtils.copyProperties(dto, entity);
        entity.setIdCategorieFichier(dto.getIdCategorieFichier());
        return entity;
    }
}