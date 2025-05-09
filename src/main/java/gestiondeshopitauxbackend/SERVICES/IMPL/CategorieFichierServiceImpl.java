package gestiondeshopitauxbackend.SERVICES.IMPL;

import gestiondeshopitauxbackend.DTOS.CategorieFichierDTO;
import gestiondeshopitauxbackend.ENTITIES.CategorieFichier;
import gestiondeshopitauxbackend.EXCEPTIONS.ResourceNotFoundException;
import gestiondeshopitauxbackend.MAPPERS.CategorieFichierMapper;
import gestiondeshopitauxbackend.REPOSITORIES.CategorieFichierRepository;
import gestiondeshopitauxbackend.SERVICES.CategorieFichierService;
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