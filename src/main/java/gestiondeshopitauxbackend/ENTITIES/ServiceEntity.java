package gestiondeshopitauxbackend.ENTITIES;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "service")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idService;
    private String nom;
    private String description;
    
    @Version
    private Long version;
    
    @ManyToOne
    @JoinColumn(name = "hopital_id")
    private Hopital hopital;
    
    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SousService> sousServices = new ArrayList<>();
    
    // Constructeur personnalisé si nécessaire
    public ServiceEntity(Long idService, String nom, String description, Hopital hopital) {
        this.idService = idService;
        this.nom = nom;
        this.description = description;
        this.hopital = hopital;
        this.sousServices = new ArrayList<>();
    }
}
