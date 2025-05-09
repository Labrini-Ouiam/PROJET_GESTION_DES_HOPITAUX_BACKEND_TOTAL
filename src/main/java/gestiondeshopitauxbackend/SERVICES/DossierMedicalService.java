package gestiondeshopitauxbackend.SERVICES;

import gestiondeshopitauxbackend.DTOS.DossierMedicalDTO;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface DossierMedicalService {
    DossierMedicalDTO createDossier(DossierMedicalDTO dossierDTO);
    DossierMedicalDTO getDossierById(Long id);
    List<DossierMedicalDTO> getDossiersByPatient(Long patientId);
    DossierMedicalDTO addFichierToDossier(Long dossierId, MultipartFile file, Long categorieId, Long typeFichierId);
    byte[] getFichierContent(Long fichierId);
    List<DossierMedicalDTO> getAllDossiers();
    void deleteDossier(Long id);
    DossierMedicalDTO updateDossier(Long id, DossierMedicalDTO dossierDTO);
    List<DossierMedicalDTO> searchDossiers(String keyword);
}