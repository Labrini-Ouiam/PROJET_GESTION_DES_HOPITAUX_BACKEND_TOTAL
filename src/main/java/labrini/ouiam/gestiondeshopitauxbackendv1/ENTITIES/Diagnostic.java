package labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Diagnostic {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDiagnostic;
    private LocalDate date;
    private String description;
    private boolean s = false;
    private Integer codeUser;
    private LocalDateTime dateLastAction = LocalDateTime.now();

    @ManyToOne
    private DossierMedical dossierMedical;

    @ManyToOne
    private Utilisateur medecin;
}