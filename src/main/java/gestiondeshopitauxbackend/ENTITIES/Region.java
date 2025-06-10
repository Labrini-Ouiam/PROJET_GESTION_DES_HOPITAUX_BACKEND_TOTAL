package gestiondeshopitauxbackend.ENTITIES;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Region {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRegion;
    private String nom;
    
    @Version
    private Long version;
    
    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    private List<Province> provinces;
    
    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    private List<Prefecture> prefectures;
}
