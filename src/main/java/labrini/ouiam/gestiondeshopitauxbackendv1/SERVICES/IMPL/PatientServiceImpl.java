package labrini.ouiam.gestiondeshopitauxbackendv1.SERVICES.IMPL;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.PatientRequestDTO;
import labrini.ouiam.gestiondeshopitauxbackendv1.REPOSITORIES.PatientRepository;
import labrini.ouiam.gestiondeshopitauxbackendv1.REPOSITORIES.TypeUserRepository;
import labrini.ouiam.gestiondeshopitauxbackendv1.SERVICES.PatientService;
import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.PatientDTO;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.TypeUser;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.Utilisateur;
import labrini.ouiam.gestiondeshopitauxbackendv1.MAPPERS.PatientMapper;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;
    
    @Autowired
    private TypeUserRepository typeUserRepository;
    
    @Autowired
    private PatientMapper patientMapper;

    @Override
    public List<PatientDTO> getAllPatients() {
        return patientRepository.findAllPatients()
                .stream()
                .map(patientMapper::utilisateurToPatientDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PatientDTO getPatientById(Long id) {
        Utilisateur patient = patientRepository.findPatientById(id);
        if (patient == null) {
            throw new RuntimeException("Patient not found with id " + id);
        }
        return patientMapper.utilisateurToPatientDTO(patient);
    }

    @Override
    public PatientDTO savePatient(PatientRequestDTO patientRequestDTO) {
        // Récupérer le type utilisateur "patient"
        TypeUser typeUserPatient = typeUserRepository.findByRole("patient");
        if (typeUserPatient == null) {
            throw new RuntimeException("Type user 'patient' not found");
        }
        
        Utilisateur utilisateur = patientMapper.patientRequestDTOToUtilisateur(patientRequestDTO, typeUserPatient);
        Utilisateur savedUtilisateur = patientRepository.save(utilisateur);
        return patientMapper.utilisateurToPatientDTO(savedUtilisateur);
    }

    @Override
    public PatientDTO updatePatient(Long id, PatientRequestDTO patientRequestDTO) {
        Utilisateur utilisateur = patientRepository.findPatientById(id);
        if (utilisateur == null) {
            throw new RuntimeException("Patient not found with id " + id);
        }
        
        patientMapper.updateUtilisateurFromDTO(patientRequestDTO, utilisateur);
        Utilisateur updatedUtilisateur = patientRepository.save(utilisateur);
        return patientMapper.utilisateurToPatientDTO(updatedUtilisateur);
    }

    @Override
    public void deletePatient(Long id) {
        Utilisateur utilisateur = patientRepository.findPatientById(id);
        if (utilisateur == null) {
            throw new RuntimeException("Patient not found with id " + id);
        }
        patientRepository.delete(utilisateur);
    }
}
