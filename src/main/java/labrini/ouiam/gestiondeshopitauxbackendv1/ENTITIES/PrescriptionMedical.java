package labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class PrescriptionMedical {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPrescriptionMedical;
    private LocalDate date;
    private String description;
    private boolean s = false;
    private Integer codeUser;
    private LocalDateTime dateLastAction = LocalDateTime.now();

    @ManyToOne
    private DossierMedical dossierMedical;

    @ManyToOne
    private Utilisateur medecin;

    @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PrescriptionMedicamentDetail> medicaments = new ArrayList<>();
}