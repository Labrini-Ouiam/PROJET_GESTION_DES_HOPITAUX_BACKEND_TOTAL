package gestiondeshopitauxbackend.ENTITIES;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

//@Entity
//@Data @NoArgsConstructor @AllArgsConstructor
//public class GererDossierMedical {
//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long idGererDossierMedical;
//    private LocalDate dateAssignation;
//    private String roleGestion;
//    private boolean s = false;
//    private int codeUser;
//    private LocalDateTime dateLastAction = LocalDateTime.now();
//
//    @ManyToOne
//    private Utilisateur utilisateur;
//
//    @ManyToOne
//    private DossierMedical dossierMedical;
//}

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class GererDossierMedical {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGererDossierMedical;
    private LocalDate dateAssignation;
    private String roleGestion;
    private boolean active = false;
    private int codeUser;
    private LocalDateTime dateLastAction = LocalDateTime.now();

    @ManyToOne
    private Utilisateur utilisateur;

    @ManyToOne
    private DossierMedical dossierMedical;
}
