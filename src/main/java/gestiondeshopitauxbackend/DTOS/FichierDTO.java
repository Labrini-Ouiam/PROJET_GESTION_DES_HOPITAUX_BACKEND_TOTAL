package gestiondeshopitauxbackend.DTOS;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FichierDTO {
    private Long id;
    private String nom;
    private String type;
    private String chemin;
    private LocalDateTime dateUpload;

    private Long dossierMedicalId;
    private Long categorieFichierId;
    private Long typeFichierId;

    // Additional info for display
    private String dossierMedicalDescription;
    private String categorieFichierNom;
    private String typeFichierNom;
}
