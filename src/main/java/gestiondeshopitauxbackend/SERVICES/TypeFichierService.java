package gestiondeshopitauxbackend.SERVICES;

import gestiondeshopitauxbackend.DTOS.TypeFichierDTO;
import java.util.List;

public interface TypeFichierService {
    TypeFichierDTO createTypeFichier(TypeFichierDTO typeFichierDTO);
    TypeFichierDTO getTypeFichierById(Long id);
    List<TypeFichierDTO> getAllTypeFichiers();
    TypeFichierDTO updateTypeFichier(Long id, TypeFichierDTO typeFichierDTO);
    void deleteTypeFichier(Long id);
    TypeFichierDTO findByNom(String nom);
}