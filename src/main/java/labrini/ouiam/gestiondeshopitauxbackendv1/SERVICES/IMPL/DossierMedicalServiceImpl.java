package labrini.ouiam.gestiondeshopitauxbackendv1.SERVICES.IMPL;

import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.DossierMedicalDTO;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.DossierMedical;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.Fichier;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.Utilisateur;
import labrini.ouiam.gestiondeshopitauxbackendv1.EXCEPTIONS.DossierMedicalNotFoundException;
import labrini.ouiam.gestiondeshopitauxbackendv1.EXCEPTIONS.ResourceNotFoundException;
import labrini.ouiam.gestiondeshopitauxbackendv1.MAPPERS.DossierMedicalMapper;
import labrini.ouiam.gestiondeshopitauxbackendv1.REPOSITORIES.DossierMedicalRepository;
import labrini.ouiam.gestiondeshopitauxbackendv1.REPOSITORIES.FichierRepository;
import labrini.ouiam.gestiondeshopitauxbackendv1.REPOSITORIES.UtilisateurRepository;
import labrini.ouiam.gestiondeshopitauxbackendv1.SERVICES.DossierMedicalService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class DossierMedicalServiceImpl implements DossierMedicalService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    private final DossierMedicalRepository dossierRepository;
    private final FichierRepository fichierRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final DossierMedicalMapper dossierMapper;

    public DossierMedicalServiceImpl(DossierMedicalRepository dossierRepository,
                                     FichierRepository fichierRepository,
                                     UtilisateurRepository utilisateurRepository,
                                     DossierMedicalMapper dossierMapper) {
        this.dossierRepository = dossierRepository;
        this.fichierRepository = fichierRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.dossierMapper = dossierMapper;
    }

    @Override
    public DossierMedicalDTO createDossier(DossierMedicalDTO dossierDTO) {
        Utilisateur patient = utilisateurRepository.findById(dossierDTO.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur", "id", dossierDTO.getPatientId()));

        DossierMedical dossier = dossierMapper.toEntity(dossierDTO);
        dossier.setPatient(patient);

        DossierMedical savedDossier = dossierRepository.save(dossier);
        return dossierMapper.toDto(savedDossier);
    }

    @Override
    public DossierMedicalDTO getDossierById(Long id) {
        DossierMedical dossier = dossierRepository.findById(id)
                .orElseThrow(() -> new DossierMedicalNotFoundException(id));
        return dossierMapper.toDto(dossier);
    }

    @Override
    public List<DossierMedicalDTO> getDossiersByPatient(Long patientId) {
        return dossierRepository.findByPatient_Id(patientId).stream()
                .map(dossierMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DossierMedicalDTO addFichierToDossier(Long dossierId, MultipartFile file, Long categorieId, Long typeFichierId) {
        DossierMedical dossier = dossierRepository.findById(dossierId)
                .orElseThrow(() -> new DossierMedicalNotFoundException(dossierId));

        try {
            File uploadPath = new File(uploadDir);
            if (!uploadPath.exists()) {
                uploadPath.mkdirs();
            }

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            Fichier fichier = new Fichier();
            fichier.setNom(file.getOriginalFilename());
            fichier.setType(file.getContentType());
            fichier.setChemin(fileName);
            fichier.setDateLastAction(LocalDateTime.now());
            fichier.setDossierMedical(dossier);

            fichierRepository.save(fichier);
            return dossierMapper.toDto(dossier);

        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de l'enregistrement du fichier", e);
        }
    }

    @Override
    public byte[] getFichierContent(Long fichierId) {
        Fichier fichier = fichierRepository.findById(fichierId)
                .orElseThrow(() -> new ResourceNotFoundException("Fichier", "id", fichierId));

        try {
            Path filePath = Paths.get(uploadDir, fichier.getChemin());
            return Files.readAllBytes(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de la lecture du fichier", e);
        }
    }

    @Override
    public List<DossierMedicalDTO> getAllDossiers() {
        return dossierRepository.findAll().stream()
                .map(dossierMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteDossier(Long id) {
        DossierMedical dossier = dossierRepository.findById(id)
                .orElseThrow(() -> new DossierMedicalNotFoundException(id));
        dossierRepository.delete(dossier);
    }

    @Override
    public DossierMedicalDTO updateDossier(Long id, DossierMedicalDTO dossierDTO) {
        DossierMedical existingDossier = dossierRepository.findById(id)
                .orElseThrow(() -> new DossierMedicalNotFoundException(id));

        dossierMapper.toEntity(dossierDTO);
        DossierMedical updatedDossier = dossierRepository.save(existingDossier);
        return dossierMapper.toDto(updatedDossier);
    }

    @Override
    public List<DossierMedicalDTO> searchDossiers(String keyword) {
        return dossierRepository.findByDescriptionContainingIgnoreCase(keyword).stream()
                .map(dossierMapper::toDto)
                .collect(Collectors.toList());
    }
}