package gestiondeshopitauxbackend.SERVICES.IMPL;

import gestiondeshopitauxbackend.DTOS.ProvinceDTO;
import gestiondeshopitauxbackend.ENTITIES.Province;
import gestiondeshopitauxbackend.ENTITIES.Region;
import gestiondeshopitauxbackend.EXCEPTIONS.ResourceNotFoundException;
import gestiondeshopitauxbackend.MAPPERS.ProvinceMapper;
import gestiondeshopitauxbackend.REPOSITORIES.ProvinceRepository;
import gestiondeshopitauxbackend.REPOSITORIES.RegionRepository;
import gestiondeshopitauxbackend.SERVICES.ProvinceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProvinceServiceImpl implements ProvinceService {

    private final ProvinceRepository provinceRepository;
    private final RegionRepository regionRepository;
    private final ProvinceMapper provinceMapper;

    @Override
    public ProvinceDTO createProvince(ProvinceDTO provinceDTO) {
        Region region = regionRepository.findByNom(provinceDTO.getRegion())
                .orElseThrow(() -> new ResourceNotFoundException("Region", "nom", provinceDTO.getRegion()));
        
        Province province = provinceMapper.toEntity(provinceDTO, region);
        Province savedProvince = provinceRepository.save(province);
        return provinceMapper.toDto(savedProvince);
    }

    @Override
    public ProvinceDTO getProvinceById(Long id) {
        Province province = provinceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Province", "id", id));
        return provinceMapper.toDto(province);
    }

    @Override
    public List<ProvinceDTO> getAllProvinces() {
        return provinceRepository.findAll().stream()
                .map(provinceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProvinceDTO> getProvincesByRegion(Long regionId) {
        return provinceRepository.findByRegion_IdRegion(regionId).stream()
                .map(provinceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProvinceDTO updateProvince(Long id, ProvinceDTO provinceDTO) {
        Province existingProvince = provinceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Province", "id", id));
        
        Region region = regionRepository.findByNom(provinceDTO.getRegion())
                .orElseThrow(() -> new ResourceNotFoundException("Region", "nom", provinceDTO.getRegion()));
        
        existingProvince.setNom(provinceDTO.getNom());
        existingProvince.setRegion(region);
        
        Province updatedProvince = provinceRepository.save(existingProvince);
        return provinceMapper.toDto(updatedProvince);
    }

    @Override
    public void deleteProvince(Long id) {
        Province province = provinceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Province", "id", id));
        provinceRepository.delete(province);
    }
}