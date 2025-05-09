package labrini.ouiam.gestiondeshopitauxbackendv1.DTOS;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

//@Data
//public class DiagnosticDTO {
//    private Long id;
//    private Long dossierMedicalId;
//    private String patientNom; // Ajout de cette propriété
//    private String patientPrenom;
//    private String description;
//    private String medecinDiagnostiqueur;
//    private String dateDiagnostic;
//}

@Data
public class DiagnosticDTO {
    private Long id;
    private LocalDate date;
    private String description;
    private boolean active;
    private LocalDateTime dateLastAction;

    private Long dossierMedicalId;
    private Long medecinId;

    // Additional info for display
    private String dossierMedicalDescription;
    private String medecinNomComplet;
}