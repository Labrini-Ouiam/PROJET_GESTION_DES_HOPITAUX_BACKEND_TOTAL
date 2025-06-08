package gestiondeshopitauxbackend.ENTITIES;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class CategorieFichier {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategorieFichier;
    private String nom;
    private boolean s = false;
    private int codeUser;
    private LocalDateTime dateLastAction = LocalDateTime.now();
}
