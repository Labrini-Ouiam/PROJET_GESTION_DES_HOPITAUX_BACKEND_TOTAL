package gestiondeshopitauxbackend.SERVICES.IMPL;

import gestiondeshopitauxbackend.DTOS.PrefectureDTO;
import gestiondeshopitauxbackend.ENTITIES.Prefecture;
import gestiondeshopitauxbackend.ENTITIES.Region;
import gestiondeshopitauxbackend.EXCEPTIONS.ResourceNotFoundException;
import gestiondeshopitauxbackend.MAPPERS.PrefectureMapper;
import gestiondeshopitauxbackend.REPOSITORIES.PrefectureRepository;
import gestiondeshopitauxbackend.REPOSITORIES.RegionRepository;
import gestiondeshopitauxbackend.SERVICES.PrefectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PrefectureServiceImpl implements PrefectureService {

    private final PrefectureRepository prefectureRepository;
    private final RegionRepository regionRepository;
    private final PrefectureMapper prefectureMapper;

    @Override
    public PrefectureDTO createPrefecture(PrefectureDTO prefectureDTO) {
        Region region = regionRepository.findByNom(prefectureDTO.getRegion())
                .orElseThrow(() -> new ResourceNotFoundException("Region", "nom", prefectureDTO.getRegion()));
        
        Prefecture prefecture = prefectureMapper.toEntity(prefectureDTO, region);
        Prefecture savedPrefecture = prefectureRepository.save(prefecture);
        return prefectureMapper.toDto(savedPrefecture);
    }

    @Override
    public PrefectureDTO getPrefectureById(Long id) {
        Prefecture prefecture = prefectureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prefecture", "id", id));
        return prefectureMapper.toDto(prefecture);
    }

    @Override
    public List<PrefectureDTO> getAllPrefectures() {
        return prefectureRepository.findAll().stream()
                .map(prefectureMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PrefectureDTO> getPrefecturesByRegion(Long regionId) {
        return prefectureRepository.findByRegion_IdRegion(regionId).stream()
                .map(prefectureMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PrefectureDTO updatePrefecture(Long id, PrefectureDTO prefectureDTO) {
        Prefecture existingPrefecture = prefectureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prefecture", "id", id));
        
        Region region = regionRepository.findByNom(prefectureDTO.getRegion())
                .orElseThrow(() -> new ResourceNotFoundException("Region", "nom", prefectureDTO.getRegion()));
        
        existingPrefecture.setNom(prefectureDTO.getNom());
        existingPrefecture.setRegion(region);
        
        Prefecture updatedPrefecture = prefectureRepository.save(existingPrefecture);
        return prefectureMapper.toDto(updatedPrefecture);
    }

    @Override
    public void deletePrefecture(Long id) {
        Prefecture prefecture = prefectureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prefecture", "id", id));
        prefectureRepository.delete(prefecture);
    }
}