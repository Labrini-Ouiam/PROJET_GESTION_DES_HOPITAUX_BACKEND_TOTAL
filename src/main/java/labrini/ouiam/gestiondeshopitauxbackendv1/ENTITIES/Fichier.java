package labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Fichier {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFichier;
    private String nom;
    private String type;
    private String chemin;
    private LocalDateTime dateLastAction;

    @ManyToOne
    private DossierMedical dossierMedical;

    @ManyToOne
    private CategorieFichier categorieFichier;

    @ManyToOne
    private TypeFichier typeFichier;
}