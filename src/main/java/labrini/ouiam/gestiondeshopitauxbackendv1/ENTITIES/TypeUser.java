package labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class TypeUser {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTypeUser;
    private String role;
    private boolean s = false;
    private int codeUser;
    private LocalDateTime dateLastAction = LocalDateTime.now();

    @OneToMany(mappedBy = "typeUser")
    private List<Utilisateur> utilisateurs;
}
