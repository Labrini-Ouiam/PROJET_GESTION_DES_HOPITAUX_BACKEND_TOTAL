package gestiondeshopitauxbackend.DTOS;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class MedicamentDTO {
    private Long id;

    @NotBlank(message = "Le nom du médicament est obligatoire")
    @Size(max = 100, message = "Le nom ne peut pas dépasser 100 caractères")
    private String nom;

    @Size(max = 500, message = "La description ne peut pas dépasser 500 caractères")
    private String description;

    private boolean active;
    private int codeUser;
    private LocalDateTime dateLastAction;

    // Méthodes utilitaires
    public String getInfoComplete() {
        return nom + (description != null ? " - " + description : "");
    }

    public String getStatut() {
        return active ? "Actif" : "Inactif";
    }
}