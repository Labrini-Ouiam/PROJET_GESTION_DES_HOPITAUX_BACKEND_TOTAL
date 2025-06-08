package labrini.ouiam.gestiondeshopitauxbackendv1.DTOS;

import jakarta.persistence.ManyToOne;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.DossierMedical;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterventionMedicalDTO {
    private Long idInterventionMedical;
    private LocalDate date;
    private LocalDateTime dateLastAction;
    private boolean s;
    private String description;
    private Long dossierMedicalId;
    private String patientNom;
    private String patientPrenom;
    private String medecinResponsable;

    private int codeUser;
}