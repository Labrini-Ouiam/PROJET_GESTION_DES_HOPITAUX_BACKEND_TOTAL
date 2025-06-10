package gestiondeshopitauxbackend.SERVICES;

import gestiondeshopitauxbackend.DTOS.RegionDTO;
import java.util.List;

public interface RegionService {
    RegionDTO createRegion(RegionDTO regionDTO);
    RegionDTO getRegionById(Long id);
    List<RegionDTO> getAllRegions();
    RegionDTO updateRegion(Long id, RegionDTO regionDTO);
    void deleteRegion(Long id);
}