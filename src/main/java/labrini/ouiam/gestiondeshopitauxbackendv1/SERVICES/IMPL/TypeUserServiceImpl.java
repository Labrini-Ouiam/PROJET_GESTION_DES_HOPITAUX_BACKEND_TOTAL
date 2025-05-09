package labrini.ouiam.gestiondeshopitauxbackendv1.SERVICES.IMPL;

import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.TypeUserDTO;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.TypeUser;
import labrini.ouiam.gestiondeshopitauxbackendv1.EXCEPTIONS.ResourceNotFoundException;
import labrini.ouiam.gestiondeshopitauxbackendv1.MAPPERS.TypeUserMapper;
import labrini.ouiam.gestiondeshopitauxbackendv1.REPOSITORIES.TypeUserRepository;
import labrini.ouiam.gestiondeshopitauxbackendv1.SERVICES.TypeUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TypeUserServiceImpl implements TypeUserService {

    private final TypeUserRepository typeUserRepository;
    private final TypeUserMapper typeUserMapper;

    public TypeUserServiceImpl(TypeUserRepository typeUserRepository,
                               TypeUserMapper typeUserMapper) {
        this.typeUserRepository = typeUserRepository;
        this.typeUserMapper = typeUserMapper;
    }

    @Override
    public TypeUserDTO createTypeUser(TypeUserDTO typeUserDTO) {
        TypeUser typeUser = typeUserMapper.toEntity(typeUserDTO);
        TypeUser savedTypeUser = typeUserRepository.save(typeUser);
        return typeUserMapper.toDto(savedTypeUser);
    }

    @Override
    public TypeUserDTO getTypeUserById(Long id) {
        TypeUser typeUser = typeUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TypeUser", "id", id));
        return typeUserMapper.toDto(typeUser);
    }

    @Override
    public List<TypeUserDTO> getAllTypeUsers() {
        return typeUserRepository.findAll().stream()
                .map(typeUserMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TypeUserDTO updateTypeUser(Long id, TypeUserDTO typeUserDTO) {
        TypeUser existingTypeUser = typeUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TypeUser", "id", id));

        existingTypeUser.setRole(typeUserDTO.getRole());

        TypeUser updatedTypeUser = typeUserRepository.save(existingTypeUser);
        return typeUserMapper.toDto(updatedTypeUser);
    }

    @Override
    public void deleteTypeUser(Long id) {
        TypeUser typeUser = typeUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TypeUser", "id", id));
        typeUserRepository.delete(typeUser);
    }

    @Override
    public TypeUserDTO findByRole(String role) {
        TypeUser typeUser = typeUserRepository.findByRole(role);
        if (typeUser == null) {
            return null;
        }
        return typeUserMapper.toDto(typeUser);
    }
}