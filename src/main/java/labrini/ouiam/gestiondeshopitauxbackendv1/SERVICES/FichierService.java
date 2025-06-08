package labrini.ouiam.gestiondeshopitauxbackendv1.SERVICES;

import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.FichierDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FichierService {
    FichierDTO getFichierById(Long id);
    List<FichierDTO> getFichiersByDossier(Long dossierId);
    FichierDTO updateFichier(Long id, FichierDTO fichierDTO);
    void deleteFichier(Long id);
    boolean fichierExists(String nomFichier, Long dossierId);

    FichierDTO uploadFichier(Long dossierId, MultipartFile file, Long categorieId, Long typeFichierId);

    byte[] downloadFichier(Long fichierId);
}