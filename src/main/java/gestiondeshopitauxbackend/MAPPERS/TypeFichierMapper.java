package gestiondeshopitauxbackend.MAPPERS;

import gestiondeshopitauxbackend.DTOS.TypeFichierDTO;
import gestiondeshopitauxbackend.ENTITIES.TypeFichier;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TypeFichierMapper {
    public TypeFichierDTO toDto(TypeFichier entity) {
        TypeFichierDTO dto = new TypeFichierDTO();
        BeanUtils.copyProperties(entity, dto);
        dto.setId(entity.getIdTypeFichier());
        return dto;
    }

    public TypeFichier toEntity(TypeFichierDTO dto) {
        TypeFichier entity = new TypeFichier();
        BeanUtils.copyProperties(dto, entity);
        entity.setIdTypeFichier(dto.getId());
        return entity;
    }
}