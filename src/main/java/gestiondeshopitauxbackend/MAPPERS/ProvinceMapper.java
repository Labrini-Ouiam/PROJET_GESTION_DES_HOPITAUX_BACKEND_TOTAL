package gestiondeshopitauxbackend.MAPPERS;

import gestiondeshopitauxbackend.DTOS.ProvinceDTO;
import gestiondeshopitauxbackend.ENTITIES.Province;
import gestiondeshopitauxbackend.ENTITIES.Region;
import org.springframework.stereotype.Component;

@Component
public class ProvinceMapper {
    
    public ProvinceDTO toDto(Province province) {
        if (province == null) return null;
        
        ProvinceDTO dto = new ProvinceDTO();
        dto.setIdProvince(province.getIdProvince());
        dto.setNom(province.getNom());
        if (province.getRegion() != null) {
            dto.setRegion(province.getRegion().getNom());
        }
        return dto;
    }
    
    public Province toEntity(ProvinceDTO dto, Region region) {
        if (dto == null) return null;
        
        Province province = new Province();
        province.setIdProvince(dto.getIdProvince());
        province.setNom(dto.getNom());
        province.setRegion(region);
        return province;
    }
}