package labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Region")
@Data @NoArgsConstructor @AllArgsConstructor
public class Region {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_region")
    private Long idRegion;
    
    @Column(name = "nom")
    private String nom;
    
    @Column(name = "s")
    private boolean s = false;
    
    @Column(name = "code_user")
    private Integer codeUser;
    
    @Column(name = "date_last_action")
    private LocalDateTime dateLastAction = LocalDateTime.now();

    @OneToMany(mappedBy = "region")
    private List<Province> provinces;

    @OneToMany(mappedBy = "region")
    private List<Prefecture> prefectures;

    @OneToMany(mappedBy = "region")
    private List<Hopital> hopitaux;
}
