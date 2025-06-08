package labrini.ouiam.gestiondeshopitauxbackendv1.SERVICES;

import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.TypeUserDTO;
import java.util.List;

public interface TypeUserService {
    TypeUserDTO createTypeUser(TypeUserDTO typeUserDTO);
    TypeUserDTO getTypeUserById(Long id);
    List<TypeUserDTO> getAllTypeUsers();
    TypeUserDTO updateTypeUser(Long id, TypeUserDTO typeUserDTO);
    void deleteTypeUser(Long id);
    TypeUserDTO findByRole(String role);
}