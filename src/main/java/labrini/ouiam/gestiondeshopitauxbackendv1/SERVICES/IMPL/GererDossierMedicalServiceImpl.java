package labrini.ouiam.gestiondeshopitauxbackendv1.SERVICES.IMPL;

import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.GererDossierMedicalDTO;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.DossierMedical;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.GererDossierMedical;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.Utilisateur;
import labrini.ouiam.gestiondeshopitauxbackendv1.EXCEPTIONS.ResourceNotFoundException;
import labrini.ouiam.gestiondeshopitauxbackendv1.MAPPERS.GererDossierMedicalMapper;
import labrini.ouiam.gestiondeshopitauxbackendv1.REPOSITORIES.DossierMedicalRepository;
import labrini.ouiam.gestiondeshopitauxbackendv1.REPOSITORIES.GererDossierMedicalRepository;
import labrini.ouiam.gestiondeshopitauxbackendv1.REPOSITORIES.UtilisateurRepository;
import labrini.ouiam.gestiondeshopitauxbackendv1.SERVICES.GererDossierMedicalService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/*@Service
@Transactional
public class GererDossierMedicalServiceImpl implements GererDossierMedicalService {

    private final GererDossierMedicalRepository gestionRepository;
    private final DossierMedicalRepository dossierRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final GererDossierMedicalMapper gestionMapper;

    public GererDossierMedicalServiceImpl(GererDossierMedicalRepository gestionRepository,
                                          DossierMedicalRepository dossierRepository,
                                          UtilisateurRepository utilisateurRepository,
                                          GererDossierMedicalMapper gestionMapper) {
        this.gestionRepository = gestionRepository;
        this.dossierRepository = dossierRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.gestionMapper = gestionMapper;
    }

    @Override
    public GererDossierMedicalDTO createGestion(GererDossierMedicalDTO gestionDTO) {
        DossierMedical dossier = dossierRepository.findById(gestionDTO.getDossierMedicalId())
                .orElseThrow(() -> new ResourceNotFoundException("DossierMedical", "id", gestionDTO.getDossierMedicalId()));

        Utilisateur utilisateur = utilisateurRepository.findById(gestionDTO.getUtilisateurId())
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur", "id", gestionDTO.getUtilisateurId()));

        GererDossierMedical gestion = gestionMapper.toEntity(gestionDTO);
        gestion.setDossierMedical(dossier);
        gestion.setUtilisateur(utilisateur);

        GererDossierMedical savedGestion = gestionRepository.save(gestion);
        return gestionMapper.toDto(savedGestion);
    }

    @Override
    public GererDossierMedicalDTO getGestionById(Long id) {
        GererDossierMedical gestion = gestionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("GererDossierMedical", "id", id));
        return gestionMapper.toDto(gestion);
    }

    @Override
    public List<GererDossierMedicalDTO> getGestionsByDossier(Long dossierId) {
        return gestionRepository.findByDossierMedical_IdDossierMedical(dossierId).stream()
                .map(gestionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<GererDossierMedicalDTO> getGestionsByUtilisateur(Long utilisateurId) {
        return gestionRepository.findByUtilisateur_IdUtilisateur(utilisateurId).stream()
                .map(gestionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public GererDossierMedicalDTO updateGestion(Long id, GererDossierMedicalDTO gestionDTO) {
        GererDossierMedical existingGestion = gestionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("GererDossierMedical", "id", id));

        gestionMapper.toEntity(gestionDTO);
        GererDossierMedical updatedGestion = gestionRepository.save(existingGestion);
        return gestionMapper.toDto(updatedGestion);
    }

    @Override
    public void deleteGestion(Long id) {
        GererDossierMedical gestion = gestionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("GererDossierMedical", "id", id));
        gestionRepository.delete(gestion);
    }
}*/

@Service
@Transactional
public class GererDossierMedicalServiceImpl implements GererDossierMedicalService {

    private final GererDossierMedicalRepository gestionRepository;
    private final DossierMedicalRepository dossierRepository;
    private final UtilisateurRepository utilisateurRepository;

    public GererDossierMedicalServiceImpl(GererDossierMedicalRepository gestionRepository,
                                          DossierMedicalRepository dossierRepository,
                                          UtilisateurRepository utilisateurRepository) {
        this.gestionRepository = gestionRepository;
        this.dossierRepository = dossierRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public GererDossierMedicalDTO createGestion(GererDossierMedicalDTO gestionDTO) {
        DossierMedical dossier = dossierRepository.findById(gestionDTO.getDossierMedicalId())
                .orElseThrow(() -> new ResourceNotFoundException("DossierMedical", "id", gestionDTO.getDossierMedicalId()));

        Utilisateur utilisateur = utilisateurRepository.findById(gestionDTO.getUtilisateurId())
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur", "id", gestionDTO.getUtilisateurId()));

        GererDossierMedical gestion = new GererDossierMedical();
        BeanUtils.copyProperties(gestionDTO, gestion);
        gestion.setDossierMedical(dossier);
        gestion.setUtilisateur(utilisateur);

        GererDossierMedical savedGestion = gestionRepository.save(gestion);
        return toDto(savedGestion);
    }

    @Override
    public GererDossierMedicalDTO getGestionById(Long id) {
        GererDossierMedical gestion = gestionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("GererDossierMedical", "id", id));
        return toDto(gestion);
    }

    @Override
    public List<GererDossierMedicalDTO> getGestionsByDossier(Long dossierId) {
        return gestionRepository.findByDossierMedical_IdDossierMedical(dossierId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<GererDossierMedicalDTO> getGestionsByUtilisateur(Long utilisateurId) {
        return gestionRepository.findByUtilisateur_IdUtilisateur(utilisateurId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public GererDossierMedicalDTO updateGestion(Long id, GererDossierMedicalDTO gestionDTO) {
        GererDossierMedical existingGestion = gestionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("GererDossierMedical", "id", id));

        BeanUtils.copyProperties(gestionDTO, existingGestion, "id", "dossierMedical", "utilisateur");

        if(gestionDTO.getDossierMedicalId() != null) {
            DossierMedical dossier = dossierRepository.findById(gestionDTO.getDossierMedicalId())
                    .orElseThrow(() -> new ResourceNotFoundException("DossierMedical", "id", gestionDTO.getDossierMedicalId()));
            existingGestion.setDossierMedical(dossier);
        }

        if(gestionDTO.getUtilisateurId() != null) {
            Utilisateur utilisateur = utilisateurRepository.findById(gestionDTO.getUtilisateurId())
                    .orElseThrow(() -> new ResourceNotFoundException("Utilisateur", "id", gestionDTO.getUtilisateurId()));
            existingGestion.setUtilisateur(utilisateur);
        }

        GererDossierMedical updatedGestion = gestionRepository.save(existingGestion);
        return toDto(updatedGestion);
    }

    @Override
    public void deleteGestion(Long id) {
        GererDossierMedical gestion = gestionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("GererDossierMedical", "id", id));
        gestionRepository.delete(gestion);
    }

    private GererDossierMedicalDTO toDto(GererDossierMedical gestion) {
        GererDossierMedicalDTO dto = new GererDossierMedicalDTO();
        BeanUtils.copyProperties(gestion, dto);

        if(gestion.getDossierMedical() != null) {
            dto.setDossierMedicalId(gestion.getDossierMedical().getIdDossierMedical());
            dto.setDossierMedicalDescription(gestion.getDossierMedical().getDescription());
        }

        if(gestion.getUtilisateur() != null) {
            dto.setUtilisateurId(gestion.getUtilisateur().getIdUtilisateur());
            dto.setUtilisateurNomComplet(gestion.getUtilisateur().getNom() + " " + gestion.getUtilisateur().getPrenom());
        }

        return dto;
    }
}