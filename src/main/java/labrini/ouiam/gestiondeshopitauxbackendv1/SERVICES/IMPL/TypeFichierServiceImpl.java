package labrini.ouiam.gestiondeshopitauxbackendv1.SERVICES.IMPL;

import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.TypeFichierDTO;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.TypeFichier;
import labrini.ouiam.gestiondeshopitauxbackendv1.EXCEPTIONS.ResourceNotFoundException;
import labrini.ouiam.gestiondeshopitauxbackendv1.MAPPERS.TypeFichierMapper;
import labrini.ouiam.gestiondeshopitauxbackendv1.REPOSITORIES.TypeFichierRepository;
import labrini.ouiam.gestiondeshopitauxbackendv1.SERVICES.TypeFichierService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TypeFichierServiceImpl implements TypeFichierService {

    private final TypeFichierRepository typeFichierRepository;
    private final TypeFichierMapper typeFichierMapper;

    public TypeFichierServiceImpl(TypeFichierRepository typeFichierRepository,
                                  TypeFichierMapper typeFichierMapper) {
        this.typeFichierRepository = typeFichierRepository;
        this.typeFichierMapper = typeFichierMapper;
    }

    @Override
    public TypeFichierDTO createTypeFichier(TypeFichierDTO typeFichierDTO) {
        TypeFichier typeFichier = typeFichierMapper.toEntity(typeFichierDTO);
        TypeFichier savedTypeFichier = typeFichierRepository.save(typeFichier);
        return typeFichierMapper.toDto(savedTypeFichier);
    }

    @Override
    public TypeFichierDTO getTypeFichierById(Long id) {
        TypeFichier typeFichier = typeFichierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TypeFichier", "id", id));
        return typeFichierMapper.toDto(typeFichier);
    }

    @Override
    public List<TypeFichierDTO> getAllTypeFichiers() {
        return typeFichierRepository.findAll().stream()
                .map(typeFichierMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TypeFichierDTO updateTypeFichier(Long id, TypeFichierDTO typeFichierDTO) {
        TypeFichier existingTypeFichier = typeFichierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TypeFichier", "id", id));

        existingTypeFichier.setNom(typeFichierDTO.getNom());

        TypeFichier updatedTypeFichier = typeFichierRepository.save(existingTypeFichier);
        return typeFichierMapper.toDto(updatedTypeFichier);
    }

    @Override
    public void deleteTypeFichier(Long id) {
        TypeFichier typeFichier = typeFichierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TypeFichier", "id", id));
        typeFichierRepository.delete(typeFichier);
    }

    @Override
    public TypeFichierDTO findByNom(String nom) {
        TypeFichier typeFichier = typeFichierRepository.findByNom(nom);
        if (typeFichier == null) {
            return null;
        }
        return typeFichierMapper.toDto(typeFichier);
    }
}