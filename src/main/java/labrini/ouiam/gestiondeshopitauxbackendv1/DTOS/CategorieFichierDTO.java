package labrini.ouiam.gestiondeshopitauxbackendv1.DTOS;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CategorieFichierDTO {
    private Long idCategorieFichier;
    private String nom;
    private boolean s;
    private LocalDateTime dateLastAction;
}