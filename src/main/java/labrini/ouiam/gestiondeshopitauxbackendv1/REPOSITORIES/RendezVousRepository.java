package labrini.ouiam.gestiondeshopitauxbackendv1.REPOSITORIES;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.RendezVous;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.Utilisateur;

@Repository
public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {
    
    List<RendezVous> findByCodePatient(Utilisateur patient);
    
    List<RendezVous> findByCodePatientAndDateRendezVousBetween(Utilisateur patient, Date debut, Date fin);
    
    List<RendezVous> findByStatut(String statut);
    
    List<RendezVous> findByCodePatient_IdUtilisateur(Long patientId);
    
    List<RendezVous> findByCodeMedecin_IdUtilisateur(Long medecinId);
}
