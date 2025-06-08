package labrini.ouiam.gestiondeshopitauxbackendv1.MAPPERS;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.RendezVousDTO;
import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.RendezVousRequestDTO;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.RendezVous;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.Utilisateur;

@Component
public class RendezVousMapper {
    
    @Autowired
    private PatientMapper patientMapper;
      public RendezVousDTO rendezVousToRendezVousDTO(RendezVous rendezVous) {
        RendezVousDTO dto = new RendezVousDTO();
        BeanUtils.copyProperties(rendezVous, dto);
        
        if (rendezVous.getCodePatient() != null) {
            dto.setPatient(patientMapper.utilisateurToPatientDTO(rendezVous.getCodePatient()));
        }
        
        return dto;
    }
    
    public RendezVous rendezVousRequestDTOToRendezVous(RendezVousRequestDTO rendezVousRequestDTO) {
        RendezVous rendezVous = new RendezVous();
        BeanUtils.copyProperties(rendezVousRequestDTO, rendezVous);
        return rendezVous;
    }
      public void updateRendezVousFromDTO(RendezVousRequestDTO rendezVousRequestDTO, RendezVous rendezVous) {
        // Don't overwrite these fields
        Long id = rendezVous.getId();
        Utilisateur codePatient = rendezVous.getCodePatient();
        Utilisateur codeMedecin = rendezVous.getCodeMedecin();
        
        // Copy all other properties
        BeanUtils.copyProperties(rendezVousRequestDTO, rendezVous);
        
        // Restore preserved fields
        rendezVous.setId(id);
        rendezVous.setCodePatient(codePatient);
        rendezVous.setCodeMedecin(codeMedecin);
    }
}
