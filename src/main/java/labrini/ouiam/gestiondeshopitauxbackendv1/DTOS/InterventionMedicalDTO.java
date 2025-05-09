package labrini.ouiam.gestiondeshopitauxbackendv1.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterventionMedicalDTO {
    private Long id;
    private LocalDate dateIntervention; // Ajout de cette propriété
    private String description;
    private Long dossierMedicalId;
    private String patientNom; // Ajout de cette propriété
    private String patientPrenom; // Ajout de cette propriété
    private String medecinResponsable;
}