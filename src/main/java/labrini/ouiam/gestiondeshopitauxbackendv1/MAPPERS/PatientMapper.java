package labrini.ouiam.gestiondeshopitauxbackendv1.MAPPERS;

import java.util.List;

import org.springframework.stereotype.Component;

import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.PatientDTO;
import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.PatientRequestDTO;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.RendezVous;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.TypeUser;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.Utilisateur;

@Component
public class PatientMapper {
    
    /**
     * Convertit un utilisateur avec rôle patient en PatientDTO
     */
    public PatientDTO utilisateurToPatientDTO(Utilisateur utilisateur) {
        PatientDTO dto = new PatientDTO();
        dto.setIdUtilisateur(utilisateur.getIdUtilisateur());
        dto.setNom(utilisateur.getNom());
        dto.setPrenom(utilisateur.getPrenom());
        dto.setCin(utilisateur.getCin());
        dto.setEmail(utilisateur.getEmail());
        dto.setTelephone(utilisateur.getTelephone());
        dto.setAdresse(utilisateur.getAdresse());
        dto.setAssuranceSocial(utilisateur.getAssuranceSocial());
        
        // Ces données ne sont plus stockées dans une table séparée
        // Elles doivent être gérées autrement (peut-être dans un service additionnel)
        // dto.setGroupeSanguin(...);
        // dto.setAntecedentsMedicaux(...);
        
        return dto;
    }
    
    /**
     * Crée un nouvel utilisateur avec rôle patient à partir d'un PatientRequestDTO
     */
    public Utilisateur patientRequestDTOToUtilisateur(PatientRequestDTO patientRequestDTO, TypeUser typeUserPatient) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(patientRequestDTO.getNom());
        utilisateur.setPrenom(patientRequestDTO.getPrenom());
        utilisateur.setEmail(patientRequestDTO.getEmail());
        utilisateur.setTelephone(patientRequestDTO.getTelephone());
        utilisateur.setPassword(patientRequestDTO.getPassword());
        utilisateur.setAssuranceSocial(patientRequestDTO.getNumeroAssurance());
        utilisateur.setTypeUser(typeUserPatient);
        
        // Ces attributs doivent être gérés autrement car ils ne sont pas dans la table Utilisateur
        // patientRequestDTO.getGroupeSanguin();
        // patientRequestDTO.getAntecedentsMedicaux();
        
        return utilisateur;
    }
    
    /**
     * Met à jour un utilisateur existant avec les données d'un PatientRequestDTO
     */
    public void updateUtilisateurFromDTO(PatientRequestDTO patientRequestDTO, Utilisateur utilisateur) {
        // Don't overwrite these fields
        Long idUtilisateur = utilisateur.getIdUtilisateur();
        TypeUser typeUser = utilisateur.getTypeUser();
        List<RendezVous> rendezVousPatient = utilisateur.getRendezVousPatient();
        boolean s = utilisateur.isS();
        
        // Mettre à jour uniquement les champs pertinents
        utilisateur.setNom(patientRequestDTO.getNom());
        utilisateur.setPrenom(patientRequestDTO.getPrenom());
        utilisateur.setEmail(patientRequestDTO.getEmail());
        utilisateur.setTelephone(patientRequestDTO.getTelephone());
        // Mettre à jour le mot de passe uniquement s'il est fourni
        if (patientRequestDTO.getPassword() != null && !patientRequestDTO.getPassword().isEmpty()) {
            utilisateur.setPassword(patientRequestDTO.getPassword());
        }
        utilisateur.setAssuranceSocial(patientRequestDTO.getNumeroAssurance());
        
        // Restaurer les champs préservés
        utilisateur.setIdUtilisateur(idUtilisateur);
        utilisateur.setTypeUser(typeUser);
        utilisateur.setRendezVousPatient(rendezVousPatient);
        utilisateur.setS(s);
    }
}
