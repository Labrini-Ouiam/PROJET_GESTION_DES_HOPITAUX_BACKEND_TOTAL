package labrini.ouiam.gestiondeshopitauxbackendv1.DTOS;

import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class DossierMedicalDTO {
    private Long idDossierMedical;
    private String description;
    private LocalDateTime dateLastAction;
    private boolean s;
    private Long patientId;
    private String patientNom;
    private String patientPrenom; // Ajout de cette propriété
    private List<FichierDTO> fichiers;
    private List<DiagnosticDTO> diagnostics;
    private List<PrescriptionMedicalDTO> prescriptions;
    private List<ResultatExamenDTO> resultats;
    private List<InterventionMedicalDTO> interventions;

    public void setDiagnostics(List<Diagnostic> diagnostics) {
        this.diagnostics = diagnostics.stream()
                .map(diagnostic -> {
                    DiagnosticDTO diagnosticDTO = new DiagnosticDTO();
                    diagnosticDTO.setIdDiagnostic(diagnostic.getIdDiagnostic());
                    diagnosticDTO.setDescription(diagnostic.getDescription());
                    diagnosticDTO.setDateLastAction(diagnostic.getDateLastAction());
                    return diagnosticDTO;
                })
                .toList();
    }

    public void setPrescriptions(List<PrescriptionMedical> prescriptions) {
        this.prescriptions = prescriptions.stream()
                .map(prescription -> {
                    PrescriptionMedicalDTO prescriptionDTO = new PrescriptionMedicalDTO();
                    prescriptionDTO.setIdPrescriptionMedical(prescription.getIdPrescriptionMedical());
                    prescriptionDTO.setDescription(prescription.getDescription());
                    prescriptionDTO.setDateLastAction(prescription.getDateLastAction());
                    return prescriptionDTO;
                })
                .toList();
    }
    public void setResultats(List<ResultatExamen> resultats) {
        this.resultats = resultats.stream()
                .map(resultat -> {
                    ResultatExamenDTO resultatDTO = new ResultatExamenDTO();
                    resultatDTO.setIdResultatExamen(resultat.getIdResultatExamen());
                    resultatDTO.setDescription(resultat.getDescription());
                    resultatDTO.setDateLastAction(resultat.getDateLastAction());
                    return resultatDTO;
                })
                .toList();
    }

    public void setFichiers(List<Fichier> fichiers) {
        this.fichiers = fichiers.stream()
                .map(fichier -> {
                    FichierDTO fichierDTO = new FichierDTO();
                    fichierDTO.setIdFichier(fichier.getIdFichier());
                    fichierDTO.setNom(fichier.getNom());
                    fichierDTO.setDateLastAction(fichier.getDateLastAction());
                    return fichierDTO;
                })
                .toList();
    }

    public void setInterventions(List<InterventionMedical> interventions) {
        this.interventions = interventions.stream()
                .map(intervention -> {
                    InterventionMedicalDTO interventionDTO = new InterventionMedicalDTO();
                    interventionDTO.setIdInterventionMedical(intervention.getIdInterventionMedical());
                    interventionDTO.setDescription(intervention.getDescription());
                    interventionDTO.setDateLastAction(intervention.getDateLastAction());
                    return interventionDTO;
                })
                .toList();
    }
}
