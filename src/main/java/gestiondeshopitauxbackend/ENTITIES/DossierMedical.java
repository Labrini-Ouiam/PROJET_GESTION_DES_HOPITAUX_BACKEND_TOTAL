package gestiondeshopitauxbackend.ENTITIES;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class DossierMedical {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDossierMedical;
    private String description;
    private LocalDateTime dateLastAction;

    @ManyToOne
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