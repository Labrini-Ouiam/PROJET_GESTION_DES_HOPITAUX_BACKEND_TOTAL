package labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rendez_vous")
public class RendezVous {
      @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdRendezVous")
    private Long id;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Date")
    private Date dateRendezVous;
      @Column(name = "Type_Consultation")
    private String typeConsultation;
    
    @Column(name = "Statut")
    private String statut; // PROGRAMMÉ, ANNULÉ, TERMINÉ
    
    @ManyToOne
    @JoinColumn(name = "Code_Patient")
    private Utilisateur codePatient;
    
    @ManyToOne
    @JoinColumn(name = "Code_Medecin")
    private Utilisateur codeMedecin;
    
    @Column(name = "S")
    private boolean s = false;
}
