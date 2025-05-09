package gestiondeshopitauxbackend.ENTITIES;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Hopital {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHopital;
    private String nom;
    private String adresse;
    private boolean s = false;
    private int codeUser;
    private LocalDateTime dateLastAction = LocalDateTime.now();

    @ManyToOne
    private Region region;

    @OneToMany(mappedBy = "hopital")
    private List<Service> services;

    @OneToMany(mappedBy = "hopital")
    private List<Utilisateur> utilisateurs;
}