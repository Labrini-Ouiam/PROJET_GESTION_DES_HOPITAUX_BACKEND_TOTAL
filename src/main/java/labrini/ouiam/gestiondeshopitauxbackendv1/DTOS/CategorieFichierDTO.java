package labrini.ouiam.gestiondeshopitauxbackendv1.DTOS;

import lombok.Data;

@Data
public class CategorieFichierDTO {
    private Long id;
    private String nom;
    private String description;
}