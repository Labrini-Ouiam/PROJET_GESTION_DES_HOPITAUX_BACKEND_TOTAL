package gestiondeshopitauxbackend.ENTITIES;

import gestiondeshopitauxbackend.ENUMS.SpecialiteInfermier;
import gestiondeshopitauxbackend.ENUMS.SpecialiteMedecin;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Utilisateur {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUtilisateur;
    private String nom;
    private String prenom;
    private String cin;
    private LocalDate dateNaissance;
    private String email;
    private String password;
    private String telephone;
    private String adresse;
    private String assuranceSocial;
    private SpecialiteMedecin specialiteMedecin;
    private SpecialiteInfermier specialiteInfermier;

    @ManyToOne
    private TypeUser typeUser;

    @ManyToOne
    private Hopital hopital;

    @OneToMany(mappedBy = "patient")
    private List<RendezVous> rendezVousPatient;

    @OneToMany(mappedBy = "medecin")
    private List<RendezVous> rendezVousMedecin;

    @OneToMany(mappedBy = "patient")
    private List<DossierMedical> dossiersMedicaux;

    @OneToMany(mappedBy = "utilisateur")
    private List<GererDossierMedical> gererDossiers;
}