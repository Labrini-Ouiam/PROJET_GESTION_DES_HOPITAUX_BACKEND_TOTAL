package labrini.ouiam.gestiondeshopitauxbackendv1.DTOS;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

//@Data
//public class PrescriptionMedicalDTO {
//    private Long id;
//    private LocalDate date;
//    private String description;
//    private Long dossierId;
//    private String patientNom;
//    private String medecinPrescripteur;
//    private List<PrescriptionMedicamentDetailDTO> medicaments;
//}

@Data
public class PrescriptionMedicalDTO {
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
    private List<PrescriptionMedicamentDetailDTO> medicaments;
}
