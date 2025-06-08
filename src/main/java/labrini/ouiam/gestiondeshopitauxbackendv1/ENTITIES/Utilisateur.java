package labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Utilisateur")
@Data @NoArgsConstructor @AllArgsConstructor
public class Utilisateur {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdUtilisateur")
    private Long idUtilisateur;
    
    @Column(name = "Nom")
    private String nom;
    
    @Column(name = "Prenom")
    private String prenom;
    
    @Column(name = "CIN")
    private String cin;
    
    @Column(name = "Date_Naissance")
    private LocalDate dateNaissance;
    
    @Column(name = "Email")
    private String email;
    
    @Column(name = "Password")
    private String password;
    
    @Column(name = "Telephone")
    private String telephone;
    
    @Column(name = "Adress")
    private String adresse;
    
    @Column(name = "Assurance_Social")
    private String assuranceSocial;
    
    @Column(name = "Specialite_Medecin")
    private String specialiteMedecin;
    
    @Column(name = "Specialite_Infermier")
    private String specialiteInfermier;
    
    @Column(name = "S")
    private boolean s = false;
      @Column(name = "Code_Hopital", insertable = false, updatable = false)
    private Long codeHopital;
    
    @Column(name = "Date_Last_Action")
    private LocalDateTime dateLastAction = LocalDateTime.now();
    
    @ManyToOne
    @JoinColumn(name = "Type_User_Id", referencedColumnName = "id_type_user")
    private TypeUser typeUser;
    
    @ManyToOne
    @JoinColumn(name = "Code_Hopital", referencedColumnName = "id_hopital")
    private Hopital hopital;

    @OneToMany(mappedBy = "codePatient")
    private List<RendezVous> rendezVousPatient;
    
    @OneToMany(mappedBy = "codeMedecin")
    private List<RendezVous> rendezVousMedecin;

    @OneToMany(mappedBy = "patient")
    private List<DossierMedical> dossiersMedicaux;

    @OneToMany(mappedBy = "utilisateur")
    private List<GererDossierMedical> gererDossiers;
    
    /**
     * Méthode de compatibilité pour assurer la transition avec la suppression de la classe Patient
     * @return idUtilisateur - l'identifiant de l'utilisateur
     */
    public Long getId() {
        return idUtilisateur;
    }
    
    /**
     * Méthode de compatibilité pour assurer la transition avec la suppression de la classe Patient
     * @param id - l'identifiant à définir pour cet utilisateur
     */
    public void setId(Long id) {
        this.idUtilisateur = id;
    }
}