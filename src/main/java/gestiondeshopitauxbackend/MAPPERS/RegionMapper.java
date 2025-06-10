package gestiondeshopitauxbackend.MAPPERS;

import gestiondeshopitauxbackend.DTOS.RegionDTO;
import gestiondeshopitauxbackend.ENTITIES.Region;
import org.springframework.stereotype.Component;

@Component
public class RegionMapper {
    
    public RegionDTO toDto(Region region) {
        if (region == null) return null;
        
        RegionDTO dto = new RegionDTO();
        dto.setIdRegion(region.getIdRegion());
        dto.setNom(region.getNom());
        return dto;
    }
    
    public Region toEntity(RegionDTO dto) {
        if (dto == null) return null;
        
        Region region = new Region();
        // Ne pas définir l'ID si c'est une mise à jour
        if (dto.getIdRegion() != null) {
            region.setIdRegion(dto.getIdRegion());
        }
        region.setNom(dto.getNom());
        return region;
    }
}