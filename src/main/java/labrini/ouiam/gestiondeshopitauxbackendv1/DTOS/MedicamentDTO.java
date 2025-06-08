package labrini.ouiam.gestiondeshopitauxbackendv1.DTOS;

import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.InterventionMedical;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.PrescriptionMedicamentDetail;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class MedicamentDTO {
    private Long idMedicament;
    private String nom;
    private String description;

    private boolean s;
    private int codeUser;
    private LocalDateTime dateLastAction;

    // Méthodes utilitaires
    public String getInfoComplete() {
        return nom + (description != null ? " - " + description : "");
    }

    public String getStatut() {
        return s ? "Actif" : "Inactif";
    }
    private List<PrescriptionMedicamentDetail> prescriptions;

}