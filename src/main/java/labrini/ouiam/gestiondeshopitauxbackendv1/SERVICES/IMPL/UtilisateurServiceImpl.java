package labrini.ouiam.gestiondeshopitauxbackendv1.SERVICES.IMPL;

import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.UtilisateurDTO;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.Utilisateur;
import labrini.ouiam.gestiondeshopitauxbackendv1.EXCEPTIONS.ResourceNotFoundException;
import labrini.ouiam.gestiondeshopitauxbackendv1.MAPPERS.UtilisateurMapper;
import labrini.ouiam.gestiondeshopitauxbackendv1.REPOSITORIES.UtilisateurRepository;
import labrini.ouiam.gestiondeshopitauxbackendv1.SERVICES.UtilisateurService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final UtilisateurMapper utilisateurMapper;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository,
                                  UtilisateurMapper utilisateurMapper) {
        this.utilisateurRepository = utilisateurRepository;
        this.utilisateurMapper = utilisateurMapper;
    }

    @Override
    public UtilisateurDTO createUser(UtilisateurDTO utilisateurDTO) {
        Utilisateur utilisateur = utilisateurMapper.toEntity(utilisateurDTO);
        Utilisateur savedUser = utilisateurRepository.save(utilisateur);
        return utilisateurMapper.toDto(savedUser);
    }

    @Override
    public UtilisateurDTO updateUser(Long id, UtilisateurDTO utilisateurDTO) {
        Utilisateur existingUser = utilisateurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur", "id", id));

        utilisateurMapper.updateUtilisateurFromDTO(utilisateurDTO, existingUser);
        Utilisateur updatedUser = utilisateurRepository.save(existingUser);
        return utilisateurMapper.toDto(updatedUser);
    }

    @Override
    public UtilisateurDTO getUserById(Long id) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur", "id", id));
        return utilisateurMapper.toDto(utilisateur);
    }

    @Override
    public List<UtilisateurDTO> getAllUsers() {
        List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
        return utilisateurs.stream()
                .map(utilisateurMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long id) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur", "id", id));
        utilisateurRepository.delete(utilisateur);
    }

    @Override
    public List<UtilisateurDTO> getMedecinsByHopital(Long hopitalId) {
        List<Utilisateur> medecins = utilisateurRepository.findByHopital_IdHopitalAndTypeUser_Role(hopitalId, "MEDECIN");
        return medecins.stream()
                .map(utilisateurMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UtilisateurDTO> searchUsers(String keyword) {
        List<Utilisateur> utilisateurs = utilisateurRepository
                .findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(keyword, keyword);
        return utilisateurs.stream()
                .map(utilisateurMapper::toDto)
                .collect(Collectors.toList());
    }
}
