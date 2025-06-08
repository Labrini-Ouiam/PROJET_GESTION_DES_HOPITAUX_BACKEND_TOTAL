package labrini.ouiam.gestiondeshopitauxbackendv1.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO pour représenter un patient (Utilisateur avec rôle "patient")
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
    private Long idUtilisateur;
    private String nom;
    private String prenom;
    private String cin;
    private String email;
    private String telephone;
    private String adresse;
    private String assuranceSocial;  // Correspond à numeroAssurance
    private String groupeSanguin;     // Stocké dans un autre champ ou comme information spécifique
    private String antecedentsMedicaux; // Stocké dans un autre champ ou comme information spécifique
}
