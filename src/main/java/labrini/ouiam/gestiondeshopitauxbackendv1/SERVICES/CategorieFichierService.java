package labrini.ouiam.gestiondeshopitauxbackendv1.SERVICES;

import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.CategorieFichierDTO;
import java.util.List;

public interface CategorieFichierService {
    CategorieFichierDTO createCategorie(CategorieFichierDTO categorieDTO);
    CategorieFichierDTO getCategorieById(Long id);
    List<CategorieFichierDTO> getAllCategories();
    CategorieFichierDTO updateCategorie(Long id, CategorieFichierDTO categorieDTO);
    void deleteCategorie(Long id);
}