package gestiondeshopitauxbackend.SERVICES;

import gestiondeshopitauxbackend.DTOS.CategorieFichierDTO;
import java.util.List;

public interface CategorieFichierService {
    CategorieFichierDTO createCategorie(CategorieFichierDTO categorieDTO);
    CategorieFichierDTO getCategorieById(Long id);
    List<CategorieFichierDTO> getAllCategories();
    CategorieFichierDTO updateCategorie(Long id, CategorieFichierDTO categorieDTO);
    void deleteCategorie(Long id);
}