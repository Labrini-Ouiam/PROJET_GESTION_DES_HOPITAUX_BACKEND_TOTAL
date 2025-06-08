package labrini.ouiam.gestiondeshopitauxbackendv1.SERVICES.IMPL;

import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.DiagnosticDTO;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.Diagnostic;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.DossierMedical;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.Utilisateur;
import labrini.ouiam.gestiondeshopitauxbackendv1.EXCEPTIONS.ResourceNotFoundException;
import labrini.ouiam.gestiondeshopitauxbackendv1.REPOSITORIES.DiagnosticRepository;
import labrini.ouiam.gestiondeshopitauxbackendv1.REPOSITORIES.DossierMedicalRepository;
import labrini.ouiam.gestiondeshopitauxbackendv1.REPOSITORIES.UtilisateurRepository;
import labrini.ouiam.gestiondeshopitauxbackendv1.SERVICES.DiagnosticService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/*
@Service
@Transactional
public class DiagnosticServiceImpl implements DiagnosticService {

    private final DiagnosticRepository diagnosticRepository;
    private final DossierMedicalRepository dossierRepository;
    private final DiagnosticMapper diagnosticMapper;

    public DiagnosticServiceImpl(DiagnosticRepository diagnosticRepository,
                                 DossierMedicalRepository dossierRepository,
                                 DiagnosticMapper diagnosticMapper) {
        this.diagnosticRepository = diagnosticRepository;
        this.dossierRepository = dossierRepository;
        this.diagnosticMapper = diagnosticMapper;
    }

    @Override
    public DiagnosticDTO createDiagnostic(DiagnosticDTO diagnosticDTO) {
        DossierMedical dossier = dossierRepository.findById(diagnosticDTO.getDossierMedicalId())
                .orElseThrow(() -> new ResourceNotFoundException("DossierMedical", "id", diagnosticDTO.getDossierMedicalId()));

        Diagnostic diagnostic = diagnosticMapper.toEntity(diagnosticDTO);
        diagnostic.setDossierMedical(dossier);

        Diagnostic savedDiagnostic = diagnosticRepository.save(diagnostic);
        return diagnosticMapper.toDto(savedDiagnostic);
    }

    @Override
    public DiagnosticDTO getDiagnosticById(Long id) {
        Diagnostic diagnostic = diagnosticRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Diagnostic", "id", id));
        return diagnosticMapper.toDto(diagnostic);
    }

    @Override
    public List<DiagnosticDTO> getDiagnosticsByDossier(Long dossierId) {
        return diagnosticRepository.findByDossierMedical_IdDossierMedical(dossierId).stream()
                .map(diagnosticMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DiagnosticDTO updateDiagnostic(Long id, DiagnosticDTO diagnosticDTO) {
        Diagnostic existingDiagnostic = diagnosticRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Diagnostic", "id", id));

        diagnosticMapper.toEntity(diagnosticDTO);
        Diagnostic updatedDiagnostic = diagnosticRepository.save(existingDiagnostic);
        return diagnosticMapper.toDto(updatedDiagnostic);
    }

    @Override
    public void deleteDiagnostic(Long id) {
        Diagnostic diagnostic = diagnosticRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Diagnostic", "id", id));
        diagnosticRepository.delete(diagnostic);
    }
}*/

@Service
@Transactional
public class DiagnosticServiceImpl implements DiagnosticService {

    private final DiagnosticRepository diagnosticRepository;
    private final DossierMedicalRepository dossierRepository;
    private final UtilisateurRepository utilisateurRepository;

    public DiagnosticServiceImpl(DiagnosticRepository diagnosticRepository,
                                 DossierMedicalRepository dossierRepository,
                                 UtilisateurRepository utilisateurRepository) {
        this.diagnosticRepository = diagnosticRepository;
        this.dossierRepository = dossierRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public DiagnosticDTO createDiagnostic(DiagnosticDTO diagnosticDTO) {
        DossierMedical dossier = dossierRepository.findById(diagnosticDTO.getDossierMedicalId())
                .orElseThrow(() -> new ResourceNotFoundException("DossierMedical", "id", diagnosticDTO.getDossierMedicalId()));

        Utilisateur medecin = utilisateurRepository.findById(diagnosticDTO.getMedecinId())
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur", "id", diagnosticDTO.getMedecinId()));

        Diagnostic diagnostic = new Diagnostic();
        BeanUtils.copyProperties(diagnosticDTO, diagnostic);
        diagnostic.setDossierMedical(dossier);
        diagnostic.setMedecin(medecin);

        Diagnostic savedDiagnostic = diagnosticRepository.save(diagnostic);
        return toDto(savedDiagnostic);
    }

    @Override
    public DiagnosticDTO getDiagnosticById(Long id) {
        Diagnostic diagnostic = diagnosticRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Diagnostic", "id", id));
        return toDto(diagnostic);
    }

    @Override
    public List<DiagnosticDTO> getDiagnosticsByDossier(Long dossierId) {
        return diagnosticRepository.findByDossierMedical_IdDossierMedical(dossierId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DiagnosticDTO updateDiagnostic(Long id, DiagnosticDTO diagnosticDTO) {
        Diagnostic existingDiagnostic = diagnosticRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Diagnostic", "id", id));

        BeanUtils.copyProperties(diagnosticDTO, existingDiagnostic, "id", "dossierMedical", "medecin");

        if(diagnosticDTO.getDossierMedicalId() != null) {
            DossierMedical dossier = dossierRepository.findById(diagnosticDTO.getDossierMedicalId())
                    .orElseThrow(() -> new ResourceNotFoundException("DossierMedical", "id", diagnosticDTO.getDossierMedicalId()));
            existingDiagnostic.setDossierMedical(dossier);
        }

        if(diagnosticDTO.getMedecinId() != null) {
            Utilisateur medecin = utilisateurRepository.findById(diagnosticDTO.getMedecinId())
                    .orElseThrow(() -> new ResourceNotFoundException("Utilisateur", "id", diagnosticDTO.getMedecinId()));
            existingDiagnostic.setMedecin(medecin);
        }

        Diagnostic updatedDiagnostic = diagnosticRepository.save(existingDiagnostic);
        return toDto(updatedDiagnostic);
    }

    @Override
    public void deleteDiagnostic(Long id) {
        Diagnostic diagnostic = diagnosticRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Diagnostic", "id", id));
        diagnosticRepository.delete(diagnostic);
    }

    private DiagnosticDTO toDto(Diagnostic diagnostic) {
        DiagnosticDTO dto = new DiagnosticDTO();
        BeanUtils.copyProperties(diagnostic, dto);

        if(diagnostic.getDossierMedical() != null) {
            dto.setDossierMedicalId(diagnostic.getDossierMedical().getIdDossierMedical());
            dto.setDossierMedicalDescription(diagnostic.getDossierMedical().getDescription());
        }

        if(diagnostic.getMedecin() != null) {
            dto.setMedecinId(diagnostic.getMedecin().getId()); // Changed from getIdUtilisateur() to getId()
            dto.setMedecinNomComplet(diagnostic.getMedecin().getNom() + " " + diagnostic.getMedecin().getPrenom());
        }

        return dto;
    }

    @Override
    public List<DiagnosticDTO> getAllDiagnostics() {
        return diagnosticRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}