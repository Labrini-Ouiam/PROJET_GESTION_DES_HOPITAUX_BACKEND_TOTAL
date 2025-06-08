package labrini.ouiam.gestiondeshopitauxbackendv1.MAPPERS;

import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.MedicamentDTO;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.Medicament;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class MedicamentMapper {
    public MedicamentDTO toDto(Medicament entity) {
        MedicamentDTO dto = new MedicamentDTO();
        BeanUtils.copyProperties(entity, dto);
        dto.setIdMedicament(entity.getIdMedicament());
        return dto;
    }

    public Medicament toEntity(MedicamentDTO dto) {
        Medicament entity = new Medicament();
        BeanUtils.copyProperties(dto, entity);
        entity.setIdMedicament(dto.getIdMedicament());
        return entity;
    }
}