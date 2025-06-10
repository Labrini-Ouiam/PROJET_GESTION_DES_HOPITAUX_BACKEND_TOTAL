package gestiondeshopitauxbackend.SERVICES;

import gestiondeshopitauxbackend.DTOS.ProvinceDTO;
import java.util.List;

public interface ProvinceService {
    ProvinceDTO createProvince(ProvinceDTO provinceDTO);
    ProvinceDTO getProvinceById(Long id);
    List<ProvinceDTO> getAllProvinces();
    List<ProvinceDTO> getProvincesByRegion(Long regionId);
    ProvinceDTO updateProvince(Long id, ProvinceDTO provinceDTO);
    void deleteProvince(Long id);
}