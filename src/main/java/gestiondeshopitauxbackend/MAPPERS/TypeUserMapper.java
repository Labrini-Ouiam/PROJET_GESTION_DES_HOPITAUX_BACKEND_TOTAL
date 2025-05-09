package gestiondeshopitauxbackend.MAPPERS;

import gestiondeshopitauxbackend.DTOS.TypeUserDTO;
import gestiondeshopitauxbackend.ENTITIES.TypeUser;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TypeUserMapper {
    public TypeUserDTO toDto(TypeUser entity) {
        TypeUserDTO dto = new TypeUserDTO();
        BeanUtils.copyProperties(entity, dto);
        dto.setId(entity.getIdTypeUser());
        return dto;
    }

    public TypeUser toEntity(TypeUserDTO dto) {
        TypeUser entity = new TypeUser();
        BeanUtils.copyProperties(dto, entity);
        entity.setIdTypeUser(dto.getId());
        return entity;
    }
}