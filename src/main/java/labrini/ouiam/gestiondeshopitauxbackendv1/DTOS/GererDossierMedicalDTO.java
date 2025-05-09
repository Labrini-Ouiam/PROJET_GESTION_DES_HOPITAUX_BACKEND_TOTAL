package labrini.ouiam.gestiondeshopitauxbackendv1.DTOS;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/*@Data
public class GererDossierMedicalDTO {
    private Long id;
    private LocalDateTime dateAssignation;
    private String roleGestion;
    private boolean active;
    private int codeUser;
    private LocalDateTime dateLastAction;

    // Relations
    private Long dossierMedicalId;
    private String dossierMedicalDescription;
    private Long utilisateurId;
    private String utilisateurNom;
    private String utilisateurPrenom;

    // Méthodes utilitaires
    public String getGestionnaireComplet() {
        return utilisateurNom + " " + utilisateurPrenom + " (" + roleGestion + ")";
    }

    public String getDossierMedicalInfo() {
        return "Dossier #" + dossierMedicalId + " - " + dossierMedicalDescription;
    }
}*/
@Data
public class GererDossierMedicalDTO {
    private Long id;
    private LocalDate dateAssignation;
    private String roleGestion;
    private boolean active;
    private int codeUser;
    private LocalDateTime dateLastAction;

    private Long utilisateurId;
    private Long dossierMedicalId;

    // Additional info for display
    private String utilisateurNomComplet;
    private String dossierMedicalDescription;
}