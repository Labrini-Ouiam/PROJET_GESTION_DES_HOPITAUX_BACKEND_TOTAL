package labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "DossierMedical")
@Data
public class DossierMedical {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dossier_medical")
    private Long idDossierMedical;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "date_last_action")
    private LocalDateTime dateLastAction;
    
    @Column(name = "s")
    private boolean s = false;    @ManyToOne
    @JoinColumn(name = "code_patient")
    private Utilisateur patient;

    @OneToMany(mappedBy = "dossierMedical", cascade = CascadeType.ALL)
    private List<Fichier> fichiers;

    @OneToMany(mappedBy = "dossierMedical", cascade = CascadeType.ALL)
    private List<Diagnostic> diagnostics;

    @OneToMany(mappedBy = "dossierMedical", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PrescriptionMedical> prescriptions;

    @OneToMany(mappedBy = "dossierMedical", cascade = CascadeType.ALL)
    private List<InterventionMedical> interventions;

    @OneToMany(mappedBy = "dossierMedical")
    private List<GererDossierMedical> gestionnaires;

    @OneToMany(mappedBy = "dossierMedical")
    private List<ResultatExamen> resultats;
}