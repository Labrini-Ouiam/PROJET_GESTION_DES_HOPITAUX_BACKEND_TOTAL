package labrini.ouiam.gestiondeshopitauxbackendv1.DTOS;

import jakarta.persistence.ManyToOne;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.CategorieFichier;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.DossierMedical;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.TypeFichier;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FichierDTO {
    private Long idFichier;
    private String nom;
    private String type;
    private String chemin;
    private LocalDateTime dateLastAction;

    private Long dossierMedicalId;
    private Long categorieFichierId;
    private Long typeFichierId;

    // Additional info for display
    private String dossierMedicalDescription;
    private String categorieFichierNom;
    private String typeFichierNom;

}
