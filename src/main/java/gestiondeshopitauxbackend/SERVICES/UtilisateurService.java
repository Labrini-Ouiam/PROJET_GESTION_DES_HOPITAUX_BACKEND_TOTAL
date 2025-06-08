package gestiondeshopitauxbackend.SERVICES;
import gestiondeshopitauxbackend.DTOS.UtilisateurDTO;

import java.util.List;

public interface UtilisateurService {
    UtilisateurDTO createUser(UtilisateurDTO utilisateurDTO);
    UtilisateurDTO updateUser(Long id, UtilisateurDTO utilisateurDTO);
    UtilisateurDTO getUserById(Long id);
    List<UtilisateurDTO> getAllUsers();
    void deleteUser(Long id);
    List<UtilisateurDTO> getMedecinsByHopital(Long hopitalId);
    List<UtilisateurDTO> searchUsers(String keyword);
}
