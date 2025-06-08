package labrini.ouiam.gestiondeshopitauxbackendv1.SERVICES;

import java.util.List;

import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.PatientDTO;
import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.PatientRequestDTO;

public interface PatientService {
    
    List<PatientDTO> getAllPatients();
    
    PatientDTO getPatientById(Long id);
    
    PatientDTO savePatient(PatientRequestDTO patientRequestDTO);
    
    PatientDTO updatePatient(Long id, PatientRequestDTO patientRequestDTO);
    
    void deletePatient(Long id);
}
