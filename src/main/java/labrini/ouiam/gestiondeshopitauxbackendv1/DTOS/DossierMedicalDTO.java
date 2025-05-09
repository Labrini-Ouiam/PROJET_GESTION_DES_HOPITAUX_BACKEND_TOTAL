package labrini.ouiam.gestiondeshopitauxbackendv1.DTOS;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class DossierMedicalDTO {
    private Long id;
    private String description;
    private Long patientId;
    private String patientNom;
    private String patientPrenom; // Ajout de cette propriété
    private LocalDateTime dateCreation;
    private List<FichierDTO> fichiers;
    private List<DiagnosticDTO> diagnostics;
    private List<PrescriptionMedicalDTO> prescriptions;
}
