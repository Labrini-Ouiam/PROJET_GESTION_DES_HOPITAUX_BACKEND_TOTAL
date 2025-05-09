package labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Region {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRegion;
    private String nom;
    private boolean s = false;
    private int codeUser;
    private LocalDateTime dateLastAction = LocalDateTime.now();

    @OneToMany(mappedBy = "region")
    private List<Province> provinces;

    @OneToMany(mappedBy = "region")
    private List<Prefecture> prefectures;

    @OneToMany(mappedBy = "region")
    private List<Hopital> hopitaux;
}
