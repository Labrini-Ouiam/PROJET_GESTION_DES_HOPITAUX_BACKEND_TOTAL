package gestiondeshopitauxbackend.SERVICES;

import gestiondeshopitauxbackend.DTOS.TypeUserDTO;
import java.util.List;

public interface TypeUserService {
    TypeUserDTO createTypeUser(TypeUserDTO typeUserDTO);
    TypeUserDTO getTypeUserById(Long id);
    List<TypeUserDTO> getAllTypeUsers();
    TypeUserDTO updateTypeUser(Long id, TypeUserDTO typeUserDTO);
    void deleteTypeUser(Long id);
    TypeUserDTO findByRole(String role);
}