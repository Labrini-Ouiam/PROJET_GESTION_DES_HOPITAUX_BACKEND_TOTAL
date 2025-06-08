package labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Hopital")
@Data @NoArgsConstructor @AllArgsConstructor
public class Hopital {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hopital")
    private Long idHopital;    @Column(name = "nom")
    private String nom;
    
    @Column(name = "adresse")
    private String adresse;
    
    @Column(name = "s")
    private boolean s = false;
    
    @Column(name = "code_user")
    private Integer codeUser;
    
    @Column(name = "date_last_action")
    private LocalDateTime dateLastAction = LocalDateTime.now();    @ManyToOne
    @JoinColumn(name = "code_region", referencedColumnName = "id_region")
    private Region region;

    @OneToMany(mappedBy = "hopital")
    private List<Service> services;

    @OneToMany(mappedBy = "hopital")
    private List<Utilisateur> utilisateurs;
}