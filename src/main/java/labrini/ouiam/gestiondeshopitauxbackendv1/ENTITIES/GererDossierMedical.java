package labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES;

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
@Table(name = "GererDossierMedical")
@Data @NoArgsConstructor @AllArgsConstructor
public class GererDossierMedical {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gerer_dossier_medical")
    private Long idGererDossierMedical;
    
    @Column(name = "date_assignation")
    private LocalDate dateAssignation;
    
    @Column(name = "role_gestion")
    private String roleGestion;
    
    @Column(name = "s")
    private boolean s = false;
    
    @Column(name = "code_user")
    private Integer codeUser;

    @Column(name = "date_last_action")
    private LocalDateTime dateLastAction = LocalDateTime.now();    @ManyToOne
    @JoinColumn(name = "code_utilisateur")
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "code_dossier_medical")
    private DossierMedical dossierMedical;
}
