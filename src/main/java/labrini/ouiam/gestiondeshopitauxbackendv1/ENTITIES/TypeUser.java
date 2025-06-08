package labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "TypeUser")
@Data @NoArgsConstructor @AllArgsConstructor
public class TypeUser {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type_user")
    private Long idTypeUser;
    
    @Column(name = "role")
    private String role;
    
    @Column(name = "s")
    private boolean s = false;
    
    @Column(name = "code_user")
    private Integer codeUser;
    
    @Column(name = "date_last_action")
    private LocalDateTime dateLastAction = LocalDateTime.now();

    @OneToMany(mappedBy = "typeUser")
    private List<Utilisateur> utilisateurs;
}
