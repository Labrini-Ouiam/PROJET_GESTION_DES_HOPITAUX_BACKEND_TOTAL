package gestiondeshopitauxbackend.REPOSITORIES;

import gestiondeshopitauxbackend.ENTITIES.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    // Recherche par hôpital et rôle
    //List<Utilisateur> findByHopital_IdUtilisateurAndTypeUser_Role(Long hopitalId, String role);
    List<Utilisateur> findByHopital_IdHopitalAndTypeUser_Role(Long hopitalId, String role);

    // Recherche par nom ou prénom contenant un mot-clé
    List<Utilisateur> findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(String nom, String prenom);
}