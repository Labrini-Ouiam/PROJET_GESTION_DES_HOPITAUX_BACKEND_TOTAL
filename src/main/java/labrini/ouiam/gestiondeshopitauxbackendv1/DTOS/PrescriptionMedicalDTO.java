package labrini.ouiam.gestiondeshopitauxbackendv1.DTOS;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.DossierMedical;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.PrescriptionMedicamentDetail;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.Utilisateur;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class PrescriptionMedicalDTO {
    private Long idPrescriptionMedical;
    private LocalDate date;
    private String description;
    private boolean s;
    private LocalDateTime dateLastAction;

    private Long dossierMedicalId;
    private Long medecinId;

    // Additional info for display
    private String dossierMedicalDescription;
    private String medecinNomComplet;
    private List<PrescriptionMedicamentDetailDTO> medicaments;
}
