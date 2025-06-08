package labrini.ouiam.gestiondeshopitauxbackendv1.SERVICES.IMPL;

import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.ResultatExamenDTO;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.DossierMedical;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.ResultatExamen;
import labrini.ouiam.gestiondeshopitauxbackendv1.EXCEPTIONS.ResourceNotFoundException;
import labrini.ouiam.gestiondeshopitauxbackendv1.MAPPERS.ResultatExamenMapper;
import labrini.ouiam.gestiondeshopitauxbackendv1.REPOSITORIES.DossierMedicalRepository;
import labrini.ouiam.gestiondeshopitauxbackendv1.REPOSITORIES.ResultatExamenRepository;
import labrini.ouiam.gestiondeshopitauxbackendv1.SERVICES.ResultatExamenService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/*@Service
@Transactional
public class ResultatExamenServiceImpl implements ResultatExamenService {

    private final ResultatExamenRepository resultatRepository;
    private final DossierMedicalRepository dossierRepository;
    private final ResultatExamenMapper resultatMapper;

    public ResultatExamenServiceImpl(ResultatExamenRepository resultatRepository,
                                     DossierMedicalRepository dossierRepository,
                                     ResultatExamenMapper resultatMapper) {
        this.resultatRepository = resultatRepository;
        this.dossierRepository = dossierRepository;
        this.resultatMapper = resultatMapper;
    }

    @Override
    public ResultatExamenDTO createResultat(ResultatExamenDTO resultatDTO) {
        DossierMedical dossier = dossierRepository.findById(resultatDTO.getDossierMedicalId())
                .orElseThrow(() -> new ResourceNotFoundException("Dossier Medical", "id", resultatDTO.getDossierMedicalId()));

        ResultatExamen resultat = resultatMapper.toEntity(resultatDTO);
        resultat.setDossierMedical(dossier);

        ResultatExamen savedResultat = resultatRepository.save(resultat);
        return resultatMapper.toDto(savedResultat);
    }

    @Override
    public ResultatExamenDTO getResultatById(Long id) {
        ResultatExamen resultat = resultatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resultat Examen", "id", id));
        return resultatMapper.toDto(resultat);
    }

    @Override
    public List<ResultatExamenDTO> getResultatsByDossier(Long dossierId) {
        return resultatRepository.findByDossierMedical_IdDossierMedical(dossierId).stream()
                .map(resultatMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ResultatExamenDTO updateResultat(Long id, ResultatExamenDTO resultatDTO) {
        ResultatExamen existingResultat = resultatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resultat Examen", "id", id));

        if (resultatDTO.getDescription() != null) {
            existingResultat.setDescription(resultatDTO.getDescription());
        }

        ResultatExamen updatedResultat = resultatRepository.save(existingResultat);
        return resultatMapper.toDto(updatedResultat);
    }

    @Override
    public void deleteResultat(Long id) {
        ResultatExamen resultat = resultatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resultat Examen", "id", id));
        resultatRepository.delete(resultat);
    }
}*/
@Service
@Transactional
public class ResultatExamenServiceImpl implements ResultatExamenService {

    private final ResultatExamenRepository resultatRepository;
    private final DossierMedicalRepository dossierRepository;

    public ResultatExamenServiceImpl(ResultatExamenRepository resultatRepository,
                                     DossierMedicalRepository dossierRepository) {
        this.resultatRepository = resultatRepository;
        this.dossierRepository = dossierRepository;
    }

    @Override
    public ResultatExamenDTO createResultat(ResultatExamenDTO resultatDTO) {
        DossierMedical dossier = dossierRepository.findById(resultatDTO.getDossierMedicalId())
                .orElseThrow(() -> new ResourceNotFoundException("DossierMedical", "id", resultatDTO.getDossierMedicalId()));

        ResultatExamen resultat = new ResultatExamen();
        BeanUtils.copyProperties(resultatDTO, resultat);
        resultat.setDossierMedical(dossier);

        ResultatExamen savedResultat = resultatRepository.save(resultat);
        return toDto(savedResultat);
    }

    @Override
    public ResultatExamenDTO getResultatById(Long id) {
        ResultatExamen resultat = resultatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ResultatExamen", "id", id));
        return toDto(resultat);
    }

    @Override
    public List<ResultatExamenDTO> getResultatsByDossier(Long dossierId) {
        return resultatRepository.findByDossierMedical_IdDossierMedical(dossierId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ResultatExamenDTO updateResultat(Long id, ResultatExamenDTO resultatDTO) {
        ResultatExamen existingResultat = resultatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ResultatExamen", "id", id));

        BeanUtils.copyProperties(resultatDTO, existingResultat, "id", "dossierMedical");

        if(resultatDTO.getDossierMedicalId() != null) {
            DossierMedical dossier = dossierRepository.findById(resultatDTO.getDossierMedicalId())
                    .orElseThrow(() -> new ResourceNotFoundException("DossierMedical", "id", resultatDTO.getDossierMedicalId()));
            existingResultat.setDossierMedical(dossier);
        }

        ResultatExamen updatedResultat = resultatRepository.save(existingResultat);
        return toDto(updatedResultat);
    }

    @Override
    public void deleteResultat(Long id) {
        ResultatExamen resultat = resultatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ResultatExamen", "id", id));
        resultatRepository.delete(resultat);
    }

    private ResultatExamenDTO toDto(ResultatExamen resultat) {
        ResultatExamenDTO dto = new ResultatExamenDTO();
        BeanUtils.copyProperties(resultat, dto);

        if(resultat.getDossierMedical() != null) {
            dto.setDossierMedicalId(resultat.getDossierMedical().getIdDossierMedical());
            dto.setDossierMedicalDescription(resultat.getDossierMedical().getDescription());
        }

        return dto;
    }
}