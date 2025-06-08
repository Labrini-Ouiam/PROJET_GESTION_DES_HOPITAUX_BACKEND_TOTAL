package labrini.ouiam.gestiondeshopitauxbackendv1.SERVICES.IMPL;

import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.InterventionMedicalDTO;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.DossierMedical;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.InterventionMedical;
import labrini.ouiam.gestiondeshopitauxbackendv1.EXCEPTIONS.ResourceNotFoundException;
import labrini.ouiam.gestiondeshopitauxbackendv1.MAPPERS.InterventionMedicalMapper;
import labrini.ouiam.gestiondeshopitauxbackendv1.REPOSITORIES.DossierMedicalRepository;
import labrini.ouiam.gestiondeshopitauxbackendv1.REPOSITORIES.InterventionMedicalRepository;
import labrini.ouiam.gestiondeshopitauxbackendv1.SERVICES.InterventionMedicalService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class InterventionMedicalServiceImpl implements InterventionMedicalService {

    private final InterventionMedicalRepository interventionRepository;
    private final DossierMedicalRepository dossierRepository;
    private final InterventionMedicalMapper interventionMapper;

    public InterventionMedicalServiceImpl(InterventionMedicalRepository interventionRepository,
                                          DossierMedicalRepository dossierRepository,
                                          InterventionMedicalMapper interventionMapper) {
        this.interventionRepository = interventionRepository;
        this.dossierRepository = dossierRepository;
        this.interventionMapper = interventionMapper;
    }

    @Override
    public InterventionMedicalDTO createIntervention(InterventionMedicalDTO interventionDTO) {
        DossierMedical dossier = dossierRepository.findById(interventionDTO.getDossierMedicalId())
                .orElseThrow(() -> new ResourceNotFoundException("DossierMedical", "id", interventionDTO.getDossierMedicalId()));

        InterventionMedical intervention = interventionMapper.toEntity(interventionDTO);
        intervention.setDossierMedical(dossier);
        intervention.setDateLastAction(LocalDateTime.now());

        InterventionMedical savedIntervention = interventionRepository.save(intervention);
        return interventionMapper.toDto(savedIntervention);
    }

    @Override
    public InterventionMedicalDTO getInterventionById(Long id) {
        InterventionMedical intervention = interventionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("InterventionMedical", "id", id));
        return interventionMapper.toDto(intervention);
    }

    @Override
    public List<InterventionMedicalDTO> getAllInterventions() {
        return interventionRepository.findAll().stream()
                .map(interventionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<InterventionMedicalDTO> getInterventionsByDossierMedical(Long dossierId) {
        DossierMedical dossier = dossierRepository.findById(dossierId)
                .orElseThrow(() -> new ResourceNotFoundException("DossierMedical", "id", dossierId));

        return interventionRepository.findByDossierMedical(dossier).stream()
                .map(interventionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public InterventionMedicalDTO updateIntervention(Long id, InterventionMedicalDTO interventionDTO) {
        InterventionMedical existingIntervention = interventionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("InterventionMedical", "id", id));

        BeanUtils.copyProperties(interventionDTO, existingIntervention, "id", "dossierMedical", "dateLastAction");
        existingIntervention.setDateLastAction(LocalDateTime.now());

        if (interventionDTO.getDossierMedicalId() != null) {
            DossierMedical dossier = dossierRepository.findById(interventionDTO.getDossierMedicalId())
                    .orElseThrow(() -> new ResourceNotFoundException("DossierMedical", "id", interventionDTO.getDossierMedicalId()));
            existingIntervention.setDossierMedical(dossier);
        }

        InterventionMedical updatedIntervention = interventionRepository.save(existingIntervention);
        return interventionMapper.toDto(updatedIntervention);
    }

    @Override
    public void deleteIntervention(Long id) {
        InterventionMedical intervention = interventionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("InterventionMedical", "id", id));
        interventionRepository.delete(intervention);
    }
}