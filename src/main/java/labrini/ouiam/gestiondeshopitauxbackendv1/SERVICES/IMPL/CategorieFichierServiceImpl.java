package labrini.ouiam.gestiondeshopitauxbackendv1.SERVICES.IMPL;

import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.CategorieFichierDTO;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.CategorieFichier;
import labrini.ouiam.gestiondeshopitauxbackendv1.EXCEPTIONS.ResourceNotFoundException;
import labrini.ouiam.gestiondeshopitauxbackendv1.MAPPERS.CategorieFichierMapper;
import labrini.ouiam.gestiondeshopitauxbackendv1.REPOSITORIES.CategorieFichierRepository;
import labrini.ouiam.gestiondeshopitauxbackendv1.SERVICES.CategorieFichierService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategorieFichierServiceImpl implements CategorieFichierService {

    private final CategorieFichierRepository categorieRepository;
    private final CategorieFichierMapper categorieMapper;

    public CategorieFichierServiceImpl(CategorieFichierRepository categorieRepository,
                                       CategorieFichierMapper categorieMapper) {
        this.categorieRepository = categorieRepository;
        this.categorieMapper = categorieMapper;
    }

    @Override
    public CategorieFichierDTO createCategorie(CategorieFichierDTO categorieDTO) {
        CategorieFichier categorie = categorieMapper.toEntity(categorieDTO);
        CategorieFichier savedCategorie = categorieRepository.save(categorie);
        return categorieMapper.toDto(savedCategorie);
    }

    @Override
    public CategorieFichierDTO getCategorieById(Long id) {
        CategorieFichier categorie = categorieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CategorieFichier", "id", id));
        return categorieMapper.toDto(categorie);
    }

    @Override
    public List<CategorieFichierDTO> getAllCategories() {
        return categorieRepository.findAll().stream()
                .map(categorieMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategorieFichierDTO updateCategorie(Long id, CategorieFichierDTO categorieDTO) {
        CategorieFichier existingCategorie = categorieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CategorieFichier", "id", id));

        categorieMapper.toEntity(categorieDTO);
        CategorieFichier updatedCategorie = categorieRepository.save(existingCategorie);
        return categorieMapper.toDto(updatedCategorie);
    }

    @Override
    public void deleteCategorie(Long id) {
        CategorieFichier categorie = categorieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CategorieFichier", "id", id));
        categorieRepository.delete(categorie);
    }
}