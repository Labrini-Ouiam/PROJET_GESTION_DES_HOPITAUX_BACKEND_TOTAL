package labrini.ouiam.gestiondeshopitauxbackendv1.SERVICES.IMPL;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.RendezVousDTO;
import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.RendezVousRequestDTO;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.RendezVous;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.Utilisateur;
import labrini.ouiam.gestiondeshopitauxbackendv1.REPOSITORIES.PatientRepository;
import labrini.ouiam.gestiondeshopitauxbackendv1.REPOSITORIES.RendezVousRepository;
import labrini.ouiam.gestiondeshopitauxbackendv1.SERVICES.RendezVousService;
import labrini.ouiam.gestiondeshopitauxbackendv1.MAPPERS.RendezVousMapper;

@Service
public class RendezVousServiceImpl implements RendezVousService {

    @Autowired
    private RendezVousRepository rendezVousRepository;
    
    @Autowired
    private PatientRepository patientRepository;
    
    @Autowired
    private RendezVousMapper rendezVousMapper;

    @Override
    public List<RendezVousDTO> getAllRendezVous() {
        return rendezVousRepository.findAll()
                .stream()
                .map(rendezVousMapper::rendezVousToRendezVousDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RendezVousDTO getRendezVousById(Long id) {
        RendezVous rendezVous = rendezVousRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rendez-vous not found with id " + id));
        return rendezVousMapper.rendezVousToRendezVousDTO(rendezVous);
    }    @Override
    public List<RendezVousDTO> getRendezVousByPatient(Long patientId) {
        Utilisateur patient = patientRepository.findPatientById(patientId);
        if (patient == null) {
            throw new RuntimeException("Patient not found with id " + patientId);
        }
        return rendezVousRepository.findByCodePatient(patient)
                .stream()
                .map(rendezVousMapper::rendezVousToRendezVousDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RendezVousDTO> getRendezVousByPatientAndDateRange(Long patientId, Date debut, Date fin) {
        Utilisateur patient = patientRepository.findPatientById(patientId);
        if (patient == null) {
            throw new RuntimeException("Patient not found with id " + patientId);
        }
        return rendezVousRepository.findByCodePatientAndDateRendezVousBetween(patient, debut, fin)
                .stream()
                .map(rendezVousMapper::rendezVousToRendezVousDTO)
                .collect(Collectors.toList());
    }    @Override
    public RendezVousDTO saveRendezVous(RendezVousRequestDTO rendezVousRequestDTO, Long patientId) {
        Utilisateur patient = patientRepository.findPatientById(patientId);
        if (patient == null) {
            throw new RuntimeException("Patient not found with id " + patientId);
        }
        
        RendezVous rendezVous = rendezVousMapper.rendezVousRequestDTOToRendezVous(rendezVousRequestDTO);
        rendezVous.setCodePatient(patient);
        
        // Set default status if not provided
        if (rendezVous.getStatut() == null) {
            rendezVous.setStatut("PROGRAMMÉ");
        }
        
        RendezVous savedRendezVous = rendezVousRepository.save(rendezVous);
        return rendezVousMapper.rendezVousToRendezVousDTO(savedRendezVous);
    }

    @Override
    public RendezVousDTO updateRendezVous(Long id, RendezVousRequestDTO rendezVousRequestDTO) {
        RendezVous rendezVous = rendezVousRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rendez-vous not found with id " + id));
        
        rendezVousMapper.updateRendezVousFromDTO(rendezVousRequestDTO, rendezVous);
        RendezVous updatedRendezVous = rendezVousRepository.save(rendezVous);
        return rendezVousMapper.rendezVousToRendezVousDTO(updatedRendezVous);
    }

    @Override
    public RendezVousDTO updateRendezVousStatus(Long id, String statut) {
        RendezVous rendezVous = rendezVousRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rendez-vous not found with id " + id));
        rendezVous.setStatut(statut);
        RendezVous updatedRendezVous = rendezVousRepository.save(rendezVous);
        return rendezVousMapper.rendezVousToRendezVousDTO(updatedRendezVous);
    }

    @Override
    public void deleteRendezVous(Long id) {
        RendezVous rendezVous = rendezVousRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rendez-vous not found with id " + id));
        rendezVousRepository.delete(rendezVous);
    }
}
