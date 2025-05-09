package gestiondeshopitauxbackend.SERVICES.IMPL;

import gestiondeshopitauxbackend.DTOS.HopitalDTO;
import gestiondeshopitauxbackend.ENTITIES.*;
import gestiondeshopitauxbackend.REPOSITORIES.*;
import gestiondeshopitauxbackend.SERVICES.HopitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class HopitalServiceImpl implements HopitalService {

    private final HopitalRepository hopitalRepository;
    private final RegionRepository regionRepository;
    private final ProvinceRepository provinceRepository;
    private final PrefectureRepository prefectureRepository;

    @Override
    public HopitalDTO createHopital(HopitalDTO dto) {
        Region region = regionRepository.findByNom(dto.getRegion())
                .orElseThrow(() -> new RuntimeException("Région non trouvée"));

        Province province = null;
        if (dto.getProvince() != null) {
            province = provinceRepository.findByNom(dto.getProvince())
                    .orElseThrow(() -> new RuntimeException("Province non trouvée"));

            if (!province.getRegion().getIdRegion().equals(region.getIdRegion())) {
                throw new RuntimeException("La province n'appartient pas à la région spécifiée");
            }
        }

        Prefecture prefecture = null;
        if (dto.getPrefecture() != null) {
            prefecture = prefectureRepository.findByNom(dto.getPrefecture())
                    .orElseThrow(() -> new RuntimeException("Préfecture non trouvée"));

            if (!prefecture.getRegion().getIdRegion().equals(region.getIdRegion())) {
                throw new RuntimeException("La préfecture n'appartient pas à la région spécifiée");
            }
        }

        Hopital hopital = new Hopital();
        hopital.setNom(dto.getNom());
        hopital.setAdresse(dto.getAdresse());
        hopital.setProvince(province);
        hopital.setPrefecture(prefecture);

        Hopital saved = hopitalRepository.save(hopital);
        dto.setIdHopital(saved.getIdHopital());
        return dto;
    }

    @Override
    public List<HopitalDTO> getAllHopitaux() {
        return hopitalRepository.findAll().stream().map(h -> {
            HopitalDTO dto = new HopitalDTO();
            dto.setIdHopital(h.getIdHopital());
            dto.setNom(h.getNom());
            dto.setAdresse(h.getAdresse());
            dto.setProvince(h.getProvince() != null ? h.getProvince().getNom() : null);
            dto.setPrefecture(h.getPrefecture() != null ? h.getPrefecture().getNom() : null);
            // Trouver la région depuis province ou préfecture
            String regionName = null;
            if (h.getProvince() != null) {
                regionName = h.getProvince().getRegion().getNom();
            } else if (h.getPrefecture() != null) {
                regionName = h.getPrefecture().getRegion().getNom();
            }
            dto.setRegion(regionName);
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public HopitalDTO getHopitalById(Long id) {
        Hopital h = hopitalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hôpital non trouvé"));

        HopitalDTO dto = new HopitalDTO();
        dto.setIdHopital(h.getIdHopital());
        dto.setNom(h.getNom());
        dto.setAdresse(h.getAdresse());
        dto.setProvince(h.getProvince() != null ? h.getProvince().getNom() : null);
        dto.setPrefecture(h.getPrefecture() != null ? h.getPrefecture().getNom() : null);
        String regionName = null;
        if (h.getProvince() != null) {
            regionName = h.getProvince().getRegion().getNom();
        } else if (h.getPrefecture() != null) {
            regionName = h.getPrefecture().getRegion().getNom();
        }
        dto.setRegion(regionName);
        return dto;
    }

    @Override
    public HopitalDTO updateHopital(Long id, HopitalDTO dto) {
        Hopital hopital = hopitalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hôpital non trouvé"));

        Region region = regionRepository.findByNom(dto.getRegion())
                .orElseThrow(() -> new RuntimeException("Région non trouvée"));

        hopital.setNom(dto.getNom());
        hopital.setAdresse(dto.getAdresse());

        if (dto.getProvince() != null) {
            Province province = provinceRepository.findByNom(dto.getProvince())
                    .orElseThrow(() -> new RuntimeException("Province non trouvée"));

            if (!province.getRegion().getIdRegion().equals(region.getIdRegion())) {
                throw new RuntimeException("La province n'appartient pas à la région spécifiée");
            }

            hopital.setProvince(province);
            hopital.setPrefecture(null); // on enlève l'autre
        }

        if (dto.getPrefecture() != null) {
            Prefecture prefecture = prefectureRepository.findByNom(dto.getPrefecture())
                    .orElseThrow(() -> new RuntimeException("Préfecture non trouvée"));

            if (!prefecture.getRegion().getIdRegion().equals(region.getIdRegion())) {
                throw new RuntimeException("La préfecture n'appartient pas à la région spécifiée");
            }

            hopital.setPrefecture(prefecture);
            hopital.setProvince(null);
        }

        Hopital updated = hopitalRepository.save(hopital);
        dto.setIdHopital(updated.getIdHopital());
        return dto;
    }

    @Override
    public void deleteHopital(Long id) {
        hopitalRepository.deleteById(id);
    }
}
