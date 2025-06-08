package gestiondeshopitauxbackend.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class ResultatExamenDTO {
//    private Long id;
//    private String description;
//    private LocalDateTime dateCreation;
//    private Long dossierMedicalId;
//    private String dossierDescription;
//}

@Data
public class ResultatExamenDTO {
    private Long id;
    private String description;
    private boolean active;
    private LocalDateTime dateLastAction;

    private Long dossierMedicalId;

    // Additional info for display
    private String dossierMedicalDescription;
}
