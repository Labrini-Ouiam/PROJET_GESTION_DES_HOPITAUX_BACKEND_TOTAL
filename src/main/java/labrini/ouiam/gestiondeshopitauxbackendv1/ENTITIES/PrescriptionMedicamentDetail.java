package labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class PrescriptionMedicamentDetail {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPrescriptionMedicamentDetail;
    private String dosage;
    private String frequence;
    private String duree;
    private boolean s = false;
    private Integer codeUser;
    private LocalDateTime dateLastAction = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "prescription_id")
    private PrescriptionMedical prescription;

    @ManyToOne
    private Medicament medicament;
}
