package gestiondeshopitauxbackend.SERVICES.IMPL;

import gestiondeshopitauxbackend.DTOS.RegionDTO;
import gestiondeshopitauxbackend.ENTITIES.Region;
import gestiondeshopitauxbackend.EXCEPTIONS.ResourceNotFoundException;
import gestiondeshopitauxbackend.MAPPERS.RegionMapper;
import gestiondeshopitauxbackend.REPOSITORIES.RegionRepository;
import gestiondeshopitauxbackend.SERVICES.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.OptimisticLockingFailureException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;
    private final RegionMapper regionMapper;

    @Override
    public RegionDTO createRegion(RegionDTO regionDTO) {
        Region region = regionMapper.toEntity(regionDTO);
        Region savedRegion = regionRepository.save(region);
        return regionMapper.toDto(savedRegion);
    }

    @Override
    public RegionDTO getRegionById(Long id) {
        Region region = regionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Region", "id", id));
        return regionMapper.toDto(region);
    }

    @Override
    public List<RegionDTO> getAllRegions() {
        return regionRepository.findAll().stream()
                .map(regionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RegionDTO updateRegion(Long id, RegionDTO regionDTO) {
        Region existingRegion = regionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Region", "id", id));
        
        existingRegion.setNom(regionDTO.getNom());
        
        try {
            Region updatedRegion = regionRepository.save(existingRegion);
            return regionMapper.toDto(updatedRegion);
        } catch (OptimisticLockingFailureException e) {
            throw new RuntimeException("La région a été modifiée par un autre utilisateur. Veuillez réessayer.", e);
        }
    }

    @Override
    public void deleteRegion(Long id) {
        Region region = regionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Region", "id", id));
        regionRepository.delete(region);
    }
}