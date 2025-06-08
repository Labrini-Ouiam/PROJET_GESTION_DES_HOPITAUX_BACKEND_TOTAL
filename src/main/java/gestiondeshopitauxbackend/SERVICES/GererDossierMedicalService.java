package gestiondeshopitauxbackend.SERVICES;

import gestiondeshopitauxbackend.DTOS.GererDossierMedicalDTO;
import java.util.List;

public interface GererDossierMedicalService {
    GererDossierMedicalDTO createGestion(GererDossierMedicalDTO gestionDTO);
    GererDossierMedicalDTO getGestionById(Long id);
    List<GererDossierMedicalDTO> getGestionsByDossier(Long dossierId);
    List<GererDossierMedicalDTO> getGestionsByUtilisateur(Long utilisateurId);
    GererDossierMedicalDTO updateGestion(Long id, GererDossierMedicalDTO gestionDTO);
    void deleteGestion(Long id);
}