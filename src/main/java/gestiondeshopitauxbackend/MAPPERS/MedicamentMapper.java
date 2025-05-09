package gestiondeshopitauxbackend.MAPPERS;

import gestiondeshopitauxbackend.DTOS.MedicamentDTO;
import gestiondeshopitauxbackend.ENTITIES.Medicament;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class MedicamentMapper {
    public MedicamentDTO toDto(Medicament entity) {
        MedicamentDTO dto = new MedicamentDTO();
        BeanUtils.copyProperties(entity, dto);
        dto.setId(entity.getIdMedicament());
        return dto;
    }

    public Medicament toEntity(MedicamentDTO dto) {
        Medicament entity = new Medicament();
        BeanUtils.copyProperties(dto, entity);
        entity.setIdMedicament(dto.getId());
        return entity;
    }
}