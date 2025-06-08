package labrini.ouiam.gestiondeshopitauxbackendv1.DTOS;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.Medicament;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.PrescriptionMedical;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
public class PrescriptionMedicamentDetailDTO {
    private Long idPrescriptionMedicamentDetail;
    private String dosage;
    private String frequence;
    private String duree;
    private boolean s;
    private LocalDateTime dateLastAction;

    private Long prescriptionId;
    private Long medicamentId;

    // Additional info for display
    private String medicamentNom;
    private String medicamentDescription;
    private String prescriptionDescription;
    }