package labrini.ouiam.gestiondeshopitauxbackendv1.DTOS;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
/*
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionMedicamentDetailDTO {
    private Long id;

    @NotNull(message = "La prescription médicale est obligatoire")
    private Long prescriptionMedicalId;

    @NotNull(message = "Le médicament est obligatoire")
    private Long medicamentId;

    @NotBlank(message = "Le dosage ne peut pas être vide")
    @Size(max = 100, message = "Le dosage ne peut pas dépasser 100 caractères")
    private String dosage;

    @NotBlank(message = "La fréquence ne peut pas être vide")
    @Size(max = 100, message = "La fréquence ne peut pas dépasser 100 caractères")
    private String frequence;

    @NotBlank(message = "La durée ne peut pas être vide")
    @Size(max = 100, message = "La durée ne peut pas dépasser 100 caractères")
    private String duree;

    // Informations liées pour affichage
    private String medicamentNom;
    private String medicamentDescription;
    private String prescriptionDescription;
    private LocalDateTime datePrescription;

    // Méthodes utilitaires
    public String getPosologieComplete() {
        return dosage + " - " + frequence + " - " + duree;
    }
}*/

@Data
public class PrescriptionMedicamentDetailDTO {
    private Long id;
    private String dosage;
    private String frequence;
    private String duree;
    private boolean active;
    private LocalDateTime dateLastAction;

    private Long prescriptionId;
    private Long medicamentId;

    // Additional info for display
    private String medicamentNom;
    private String medicamentDescription;
    private String prescriptionDescription;
}