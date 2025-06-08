package labrini.ouiam.gestiondeshopitauxbackendv1.DTOS;
import jakarta.persistence.ManyToOne;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.DossierMedical;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.Utilisateur;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
public class DiagnosticDTO {
    private Long idDiagnostic;
    private LocalDateTime dateLastAction;
    private String description;
    private int codeUser;

    private boolean s;
    private Long dossierMedicalId;
    private Long medecinId;
    private String dossierMedicalDescription;
    private String medecinNomComplet;

    private LocalDate date;
}