package labrini.ouiam.gestiondeshopitauxbackendv1.SERVICES.IMPL;

import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.FichierDTO;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.CategorieFichier;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.DossierMedical;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.Fichier;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.TypeFichier;
import labrini.ouiam.gestiondeshopitauxbackendv1.EXCEPTIONS.ResourceNotFoundException;
import labrini.ouiam.gestiondeshopitauxbackendv1.REPOSITORIES.CategorieFichierRepository;
import labrini.ouiam.gestiondeshopitauxbackendv1.REPOSITORIES.DossierMedicalRepository;
import labrini.ouiam.gestiondeshopitauxbackendv1.REPOSITORIES.FichierRepository;
import labrini.ouiam.gestiondeshopitauxbackendv1.REPOSITORIES.TypeFichierRepository;
import labrini.ouiam.gestiondeshopitauxbackendv1.SERVICES.FichierService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
public class FichierServiceImpl implements FichierService {

    private final FichierRepository fichierRepository;
    private final DossierMedicalRepository dossierMedicalRepository;
    private final CategorieFichierRepository categorieFichierRepository;
    private final TypeFichierRepository typeFichierRepository;
    @Value("${file.upload-dir}")
    private String uploadDir;

    public FichierServiceImpl(FichierRepository fichierRepository,
                              DossierMedicalRepository dossierMedicalRepository,
                              CategorieFichierRepository categorieFichierRepository,
                              TypeFichierRepository typeFichierRepository) {
        this.fichierRepository = fichierRepository;
        this.dossierMedicalRepository = dossierMedicalRepository;
        this.categorieFichierRepository = categorieFichierRepository;
        this.typeFichierRepository = typeFichierRepository;
    }

    @Override
    public FichierDTO getFichierById(Long id) {
        Fichier fichier = fichierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fichier", "id", id));

        FichierDTO fichierDTO = new FichierDTO();
        BeanUtils.copyProperties(fichier, fichierDTO);

        if(fichier.getDossierMedical() != null) {
            fichierDTO.setDossierMedicalId(fichier.getDossierMedical().getIdDossierMedical());
            fichierDTO.setDossierMedicalDescription(fichier.getDossierMedical().getDescription());
        }

        if(fichier.getCategorieFichier() != null) {
            fichierDTO.setCategorieFichierId(fichier.getCategorieFichier().getIdCategorieFichier());
            fichierDTO.setCategorieFichierNom(fichier.getCategorieFichier().getNom());
        }

        if(fichier.getTypeFichier() != null) {
            fichierDTO.setTypeFichierId(fichier.getTypeFichier().getIdTypeFichier());
            fichierDTO.setTypeFichierNom(fichier.getTypeFichier().getNom());
        }

        return fichierDTO;
    }

    @Override
    public List<FichierDTO> getFichiersByDossier(Long dossierId) {
        List<Fichier> fichiers = fichierRepository.findByDossierMedical_IdDossierMedical(dossierId);
        return fichiers.stream().map(fichier -> {
            FichierDTO dto = new FichierDTO();
            BeanUtils.copyProperties(fichier, dto);
            dto.setDossierMedicalId(dossierId);
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public FichierDTO updateFichier(Long id, FichierDTO fichierDTO) {
        Fichier existingFichier = fichierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fichier", "id", id));

        BeanUtils.copyProperties(fichierDTO, existingFichier, "id", "dossierMedical", "categorieFichier", "typeFichier");

        if(fichierDTO.getDossierMedicalId() != null) {
            DossierMedical dossier = dossierMedicalRepository.findById(fichierDTO.getDossierMedicalId())
                    .orElseThrow(() -> new ResourceNotFoundException("DossierMedical", "id", fichierDTO.getDossierMedicalId()));
            existingFichier.setDossierMedical(dossier);
        }

        if(fichierDTO.getCategorieFichierId() != null) {
            CategorieFichier categorie = categorieFichierRepository.findById(fichierDTO.getCategorieFichierId())
                    .orElseThrow(() -> new ResourceNotFoundException("CategorieFichier", "id", fichierDTO.getCategorieFichierId()));
            existingFichier.setCategorieFichier(categorie);
        }

        if(fichierDTO.getTypeFichierId() != null) {
            TypeFichier type = typeFichierRepository.findById(fichierDTO.getTypeFichierId())
                    .orElseThrow(() -> new ResourceNotFoundException("TypeFichier", "id", fichierDTO.getTypeFichierId()));
            existingFichier.setTypeFichier(type);
        }

        Fichier updatedFichier = fichierRepository.save(existingFichier);
        FichierDTO updatedDto = new FichierDTO();
        BeanUtils.copyProperties(updatedFichier, updatedDto);
        return updatedDto;
    }

    @Override
    public void deleteFichier(Long id) {
        Fichier fichier = fichierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fichier", "id", id));
        fichierRepository.delete(fichier);
    }

    @Override
    public boolean fichierExists(String nomFichier, Long dossierId) {
        return fichierRepository.findByNomAndDossierMedical_IdDossierMedical(nomFichier, dossierId).isPresent();
    }

    @Override
    public FichierDTO uploadFichier(Long dossierId, MultipartFile file, Long categorieId, Long typeFichierId) {
        try {
            DossierMedical dossier = dossierMedicalRepository.findById(dossierId)
                    .orElseThrow(() -> new ResourceNotFoundException("DossierMedical", "id", dossierId));

            CategorieFichier categorie = categorieFichierRepository.findById(categorieId)
                    .orElseThrow(() -> new ResourceNotFoundException("CategorieFichier", "id", categorieId));

            TypeFichier typeFichier = typeFichierRepository.findById(typeFichierId)
                    .orElseThrow(() -> new ResourceNotFoundException("TypeFichier", "id", typeFichierId));

            // Créer le répertoire s'il n'existe pas
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Générer un nom de fichier unique
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);

            // Enregistrer le fichier
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Créer et sauvegarder l'entité Fichier
            Fichier fichier = new Fichier();
            fichier.setNom(file.getOriginalFilename());
            fichier.setType(file.getContentType());
            fichier.setChemin(filePath.toString());
            fichier.setDateLastAction(LocalDateTime.now());
            fichier.setDossierMedical(dossier);
            fichier.setCategorieFichier(categorie);
            fichier.setTypeFichier(typeFichier);

            Fichier savedFichier = fichierRepository.save(fichier);
            return toDto(savedFichier);

        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de l'enregistrement du fichier", e);
        }
    }

    @Override
    public byte[] downloadFichier(Long fichierId) {
        Fichier fichier = fichierRepository.findById(fichierId)
                .orElseThrow(() -> new ResourceNotFoundException("Fichier", "id", fichierId));

        try {
            Path filePath = Paths.get(fichier.getChemin());
            return Files.readAllBytes(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de la lecture du fichier", e);
        }
    }

    private FichierDTO toDto(Fichier fichier) {
        FichierDTO dto = new FichierDTO();
        BeanUtils.copyProperties(fichier, dto);

        if(fichier.getDossierMedical() != null) {
            dto.setDossierMedicalId(fichier.getDossierMedical().getIdDossierMedical());
        }

        if(fichier.getCategorieFichier() != null) {
            dto.setCategorieFichierId(fichier.getCategorieFichier().getIdCategorieFichier());
            dto.setCategorieFichierNom(fichier.getCategorieFichier().getNom());
        }

        if(fichier.getTypeFichier() != null) {
            dto.setTypeFichierId(fichier.getTypeFichier().getIdTypeFichier());
            dto.setTypeFichierNom(fichier.getTypeFichier().getNom());
        }

        return dto;
    }
}