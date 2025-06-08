package labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Service")
@Data @NoArgsConstructor @AllArgsConstructor
public class Service {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_service")
    private Long idService;
    
    @Column(name = "nom")
    private String nom;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "s")
    private boolean s = false;
    
    @Column(name = "code_user")
    private Integer codeUser;
    
    @Column(name = "date_last_action")
    private LocalDateTime dateLastAction = LocalDateTime.now();    @ManyToOne
    @JoinColumn(name = "code_hopital", referencedColumnName = "id_hopital")
    private Hopital hopital;

    @OneToMany(mappedBy = "service")
    private List<SousService> sousServices;
}