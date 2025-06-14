package labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

//@Entity
//@Data @NoArgsConstructor @AllArgsConstructor
//public class ResultatExamen {
//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long idResultatExamen;
//    private String description;
//    private boolean s = false;
//    private int codeUser;
//    private LocalDateTime dateLastAction = LocalDateTime.now();
//
//    @ManyToOne
//    private DossierMedical dossierMedical;
//}

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class ResultatExamen {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idResultatExamen;
    private String description;
    private boolean s = false;
    private Integer codeUser;
    private LocalDateTime dateLastAction = LocalDateTime.now();

    @ManyToOne
    private DossierMedical dossierMedical;
}
