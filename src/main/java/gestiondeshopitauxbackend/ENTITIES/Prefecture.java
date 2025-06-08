package gestiondeshopitauxbackend.ENTITIES;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Prefecture {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPrefecture;
    private String nom;

    @ManyToOne
    private Region region;

    @OneToMany(mappedBy = "prefecture")
    private List<Hopital> hopitaux;
}
