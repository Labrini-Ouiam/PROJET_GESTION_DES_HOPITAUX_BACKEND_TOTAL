package gestiondeshopitauxbackend.ENTITIES;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idService;

    private String nom;
    private String description;

    @ManyToOne
    private Hopital hopital;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    private List<SousService> sousServices;
}
