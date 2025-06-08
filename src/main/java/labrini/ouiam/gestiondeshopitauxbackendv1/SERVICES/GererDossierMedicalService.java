package labrini.ouiam.gestiondeshopitauxbackendv1.SERVICES;

import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.GererDossierMedicalDTO;
import java.util.List;

public interface GererDossierMedicalService {
    GererDossierMedicalDTO createGestion(GererDossierMedicalDTO gestionDTO);
    GererDossierMedicalDTO getGestionById(Long id);
    List<GererDossierMedicalDTO> getGestionsByDossier(Long dossierId);
    List<GererDossierMedicalDTO> getGestionsByUtilisateur(Long utilisateurId);
    GererDossierMedicalDTO updateGestion(Long id, GererDossierMedicalDTO gestionDTO);
    void deleteGestion(Long id);
}