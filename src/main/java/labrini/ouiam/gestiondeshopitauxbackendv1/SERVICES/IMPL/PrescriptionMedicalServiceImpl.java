package labrini.ouiam.gestiondeshopitauxbackendv1.SERVICES.IMPL;

import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.PrescriptionMedicalDTO;
import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.PrescriptionMedicamentDetailDTO;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.DossierMedical;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.PrescriptionMedical;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.PrescriptionMedicamentDetail;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.Utilisateur;
import labrini.ouiam.gestiondeshopitauxbackendv1.EXCEPTIONS.ResourceNotFoundException;
import labrini.ouiam.gestiondeshopitauxbackendv1.MAPPERS.PrescriptionMapper;
import labrini.ouiam.gestiondeshopitauxbackendv1.REPOSITORIES.DossierMedicalRepository;
import labrini.ouiam.gestiondeshopitauxbackendv1.REPOSITORIES.PrescriptionMedicalRepository;
import labrini.ouiam.gestiondeshopitauxbackendv1.REPOSITORIES.PrescriptionMedicamentDetailRepository;
import labrini.ouiam.gestiondeshopitauxbackendv1.REPOSITORIES.UtilisateurRepository;
import labrini.ouiam.gestiondeshopitauxbackendv1.SERVICES.PrescriptionMedicalService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/*
@Service
@Transactional
public class PrescriptionMedicalServiceImpl implements PrescriptionMedicalService {

    private final PrescriptionMedicalRepository prescriptionRepository;
    private final DossierMedicalRepository dossierRepository;
    private final PrescriptionMapper prescriptionMapper;

    public PrescriptionMedicalServiceImpl(PrescriptionMedicalRepository prescriptionRepository,
                                          DossierMedicalRepository dossierRepository,
                                          PrescriptionMapper prescriptionMapper) {
        this.prescriptionRepository = prescriptionRepository;
        this.dossierRepository = dossierRepository;
        this.prescriptionMapper = prescriptionMapper;
    }

    @Override
    public PrescriptionMedicalDTO createPrescription(PrescriptionMedicalDTO prescriptionDTO) {
        DossierMedical dossier = dossierRepository.findById(prescriptionDTO.getDossierId())
                .orElseThrow(() -> new ResourceNotFoundException("DossierMedical", "id", prescriptionDTO.getDossierId()));

        PrescriptionMedical prescription = prescriptionMapper.toEntity(prescriptionDTO);
        prescription.setDossierMedical(dossier);

        PrescriptionMedical savedPrescription = prescriptionRepository.save(prescription);
        return prescriptionMapper.toDto(savedPrescription);
    }

    @Override
    public PrescriptionMedicalDTO getPrescriptionById(Long id) {
        PrescriptionMedical prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PrescriptionMedical", "id", id));
        return prescriptionMapper.toDto(prescription);
    }

    @Override
    public List<PrescriptionMedicalDTO> getPrescriptionsByDossier(Long dossierId) {
        return prescriptionRepository.findByDossierMedical_IdDossierMedical(dossierId).stream()
                .map(prescriptionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PrescriptionMedicalDTO updatePrescription(Long id, PrescriptionMedicalDTO prescriptionDTO) {
        PrescriptionMedical existingPrescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PrescriptionMedical", "id", id));

        prescriptionMapper.toEntity(prescriptionDTO);
        PrescriptionMedical updatedPrescription = prescriptionRepository.save(existingPrescription);
        return prescriptionMapper.toDto(updatedPrescription);
    }

    @Override
    public void deletePrescription(Long id) {
        PrescriptionMedical prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PrescriptionMedical", "id", id));
        prescriptionRepository.delete(prescription);
    }
}*/
@Service
@Transactional
public class PrescriptionMedicalServiceImpl implements PrescriptionMedicalService {

    private final PrescriptionMedicalRepository prescriptionRepository;
    private final DossierMedicalRepository dossierRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final PrescriptionMedicamentDetailRepository detailRepository;

    public PrescriptionMedicalServiceImpl(PrescriptionMedicalRepository prescriptionRepository,
                                          DossierMedicalRepository dossierRepository,
                                          UtilisateurRepository utilisateurRepository,
                                          PrescriptionMedicamentDetailRepository detailRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.dossierRepository = dossierRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.detailRepository = detailRepository;
    }

    @Override
    public PrescriptionMedicalDTO createPrescription(PrescriptionMedicalDTO prescriptionDTO) {
        DossierMedical dossier = dossierRepository.findById(prescriptionDTO.getDossierMedicalId())
                .orElseThrow(() -> new ResourceNotFoundException("DossierMedical", "id", prescriptionDTO.getDossierMedicalId()));

        Utilisateur medecin = utilisateurRepository.findById(prescriptionDTO.getMedecinId())
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur", "id", prescriptionDTO.getMedecinId()));

        PrescriptionMedical prescription = new PrescriptionMedical();
        BeanUtils.copyProperties(prescriptionDTO, prescription);
        prescription.setDossierMedical(dossier);
        prescription.setMedecin(medecin);

        PrescriptionMedical savedPrescription = prescriptionRepository.save(prescription);
        return toDto(savedPrescription);
    }

    @Override
    public PrescriptionMedicalDTO getPrescriptionById(Long id) {
        PrescriptionMedical prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PrescriptionMedical", "id", id));
        return toDto(prescription);
    }

    @Override
    public List<PrescriptionMedicalDTO> getPrescriptionsByDossier(Long dossierId) {
        return prescriptionRepository.findByDossierMedical_IdDossierMedical(dossierId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PrescriptionMedicalDTO updatePrescription(Long id, PrescriptionMedicalDTO prescriptionDTO) {
        PrescriptionMedical existingPrescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PrescriptionMedical", "id", id));

        BeanUtils.copyProperties(prescriptionDTO, existingPrescription, "id", "dossierMedical", "medecin", "medicaments");

        if(prescriptionDTO.getDossierMedicalId() != null) {
            DossierMedical dossier = dossierRepository.findById(prescriptionDTO.getDossierMedicalId())
                    .orElseThrow(() -> new ResourceNotFoundException("DossierMedical", "id", prescriptionDTO.getDossierMedicalId()));
            existingPrescription.setDossierMedical(dossier);
        }

        if(prescriptionDTO.getMedecinId() != null) {
            Utilisateur medecin = utilisateurRepository.findById(prescriptionDTO.getMedecinId())
                    .orElseThrow(() -> new ResourceNotFoundException("Utilisateur", "id", prescriptionDTO.getMedecinId()));
            existingPrescription.setMedecin(medecin);
        }

        PrescriptionMedical updatedPrescription = prescriptionRepository.save(existingPrescription);
        return toDto(updatedPrescription);
    }

    @Override
    public void deletePrescription(Long id) {
        PrescriptionMedical prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PrescriptionMedical", "id", id));
        prescriptionRepository.delete(prescription);
    }

    private PrescriptionMedicalDTO toDto(PrescriptionMedical prescription) {
        PrescriptionMedicalDTO dto = new PrescriptionMedicalDTO();
        BeanUtils.copyProperties(prescription, dto);

        if(prescription.getDossierMedical() != null) {
            dto.setDossierMedicalId(prescription.getDossierMedical().getIdDossierMedical());
            dto.setDossierMedicalDescription(prescription.getDossierMedical().getDescription());
        }

        if(prescription.getMedecin() != null) {
            dto.setMedecinId(prescription.getMedecin().getIdUtilisateur());
            dto.setMedecinNomComplet(prescription.getMedecin().getNom() + " " + prescription.getMedecin().getPrenom());
        }

        // Map medicaments if needed
        if(prescription.getMedicaments() != null && !prescription.getMedicaments().isEmpty()) {
            dto.setMedicaments(prescription.getMedicaments().stream()
                    .map(this::toDetailDto)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    private PrescriptionMedicamentDetailDTO toDetailDto(PrescriptionMedicamentDetail detail) {
        PrescriptionMedicamentDetailDTO dto = new PrescriptionMedicamentDetailDTO();
        BeanUtils.copyProperties(detail, dto);

        if(detail.getMedicament() != null) {
            dto.setMedicamentId(detail.getMedicament().getIdMedicament());
            dto.setMedicamentNom(detail.getMedicament().getNom());
            dto.setMedicamentDescription(detail.getMedicament().getDescription());
        }

        return dto;
    }
}