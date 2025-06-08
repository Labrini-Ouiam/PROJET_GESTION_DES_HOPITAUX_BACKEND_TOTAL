package labrini.ouiam.gestiondeshopitauxbackendv1.SERVICES;

import java.util.Date;
import java.util.List;

import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.RendezVousDTO;
import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.RendezVousRequestDTO;

public interface RendezVousService {
    
    List<RendezVousDTO> getAllRendezVous();
    
    RendezVousDTO getRendezVousById(Long id);
    
    List<RendezVousDTO> getRendezVousByPatient(Long patientId);
    
    List<RendezVousDTO> getRendezVousByPatientAndDateRange(Long patientId, Date debut, Date fin);
    
    RendezVousDTO saveRendezVous(RendezVousRequestDTO rendezVousRequestDTO, Long patientId);
    
    RendezVousDTO updateRendezVous(Long id, RendezVousRequestDTO rendezVousRequestDTO);
    
    RendezVousDTO updateRendezVousStatus(Long id, String statut);
    
    void deleteRendezVous(Long id);
}
