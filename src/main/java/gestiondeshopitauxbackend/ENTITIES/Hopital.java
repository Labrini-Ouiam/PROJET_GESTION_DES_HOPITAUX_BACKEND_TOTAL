package gestiondeshopitauxbackend.ENTITIES;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Hopital {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHopital;
    private String nom;
    private String adresse;

    @ManyToOne
    private Province province;

    @ManyToOne
    private Prefecture prefecture;

    @OneToMany(mappedBy = "hopital")
    private List<ServiceEntity> services;

    @OneToMany(mappedBy = "hopital")
    private List<Utilisateur> utilisateurs;

}