package labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class SpecialiteInfermier {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSpecialiteInfermier;
    private String nom;
    private String description;
    private boolean s = false;
    private Integer codeUser;
    private LocalDateTime dateLastAction = LocalDateTime.now();
}
