package gestiondeshopitauxbackend.ENTITIES;

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

    @OneToMany(mappedBy = "region")
    private List<Province> provinces;

    @OneToMany(mappedBy = "region")
    private List<Prefecture> prefectures;

}
