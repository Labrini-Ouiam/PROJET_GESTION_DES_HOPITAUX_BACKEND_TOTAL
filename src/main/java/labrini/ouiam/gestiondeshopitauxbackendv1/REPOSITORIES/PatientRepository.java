package labrini.ouiam.gestiondeshopitauxbackendv1.REPOSITORIES;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.Utilisateur;

@Repository
public interface PatientRepository extends JpaRepository<Utilisateur, Long> {
    /**
     * Récupère tous les utilisateurs qui ont le rôle "patient"
     */
    @Query("SELECT u FROM Utilisateur u WHERE u.typeUser.role = 'patient'")
    List<Utilisateur> findAllPatients();
    
    /**
     * Recherche un utilisateur qui a le rôle "patient" par son ID
     */
    @Query("SELECT u FROM Utilisateur u WHERE u.idUtilisateur = :id AND u.typeUser.role = 'patient'")
    Utilisateur findPatientById(Long id);
}
