package labrini.ouiam.gestiondeshopitauxbackendv1.DTOS;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TypeFichierDTO {
    private Long idTypeFichier;
    private String nom;
    private boolean s ;
    private int codeUser;
    private LocalDateTime dateLastAction;
}