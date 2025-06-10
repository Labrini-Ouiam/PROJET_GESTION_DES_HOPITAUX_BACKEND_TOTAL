package gestiondeshopitauxbackend.MAPPERS;

import gestiondeshopitauxbackend.DTOS.PrefectureDTO;
import gestiondeshopitauxbackend.ENTITIES.Prefecture;
import gestiondeshopitauxbackend.ENTITIES.Region;
import org.springframework.stereotype.Component;

@Component
public class PrefectureMapper {
    
    public PrefectureDTO toDto(Prefecture prefecture) {
        if (prefecture == null) return null;
        
        PrefectureDTO dto = new PrefectureDTO();
        dto.setIdPrefecture(prefecture.getIdPrefecture());
        dto.setNom(prefecture.getNom());
        if (prefecture.getRegion() != null) {
            dto.setRegion(prefecture.getRegion().getNom());
        }
        return dto;
    }
    
    public Prefecture toEntity(PrefectureDTO dto, Region region) {
        if (dto == null) return null;
        
        Prefecture prefecture = new Prefecture();
        prefecture.setIdPrefecture(dto.getIdPrefecture());
        prefecture.setNom(dto.getNom());
        prefecture.setRegion(region);
        return prefecture;
    }
}