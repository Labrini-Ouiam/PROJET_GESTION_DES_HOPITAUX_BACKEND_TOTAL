package labrini.ouiam.gestiondeshopitauxbackendv1.SERVICES;

import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.InterventionMedicalDTO;
import java.util.List;

public interface InterventionMedicalService {
    InterventionMedicalDTO createIntervention(InterventionMedicalDTO interventionDTO);
    InterventionMedicalDTO getInterventionById(Long id);
    List<InterventionMedicalDTO> getAllInterventions();
    List<InterventionMedicalDTO> getInterventionsByDossierMedical(Long dossierId);
    InterventionMedicalDTO updateIntervention(Long id, InterventionMedicalDTO interventionDTO);
    void deleteIntervention(Long id);
}