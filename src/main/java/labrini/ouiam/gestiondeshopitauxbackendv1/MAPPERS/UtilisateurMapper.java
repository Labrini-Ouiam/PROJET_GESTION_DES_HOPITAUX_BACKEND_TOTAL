package labrini.ouiam.gestiondeshopitauxbackendv1.MAPPERS;

import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.UtilisateurDTO;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.Utilisateur;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurMapper {
    public UtilisateurDTO toDto(Utilisateur entity) {
        UtilisateurDTO dto = new UtilisateurDTO();
        BeanUtils.copyProperties(entity, dto);
        dto.setIdUtilisateur(entity.getId());
        if (entity.getTypeUser() != null) {
            dto.setRole(entity.getTypeUser().getRole());
        }


        return dto;
    }

    public Utilisateur toEntity(UtilisateurDTO dto) {
        Utilisateur entity = new Utilisateur();
        BeanUtils.copyProperties(dto, entity);
        entity.setId(dto.getIdUtilisateur());
        return entity;
    }

    public void updateUtilisateurFromDTO(UtilisateurDTO dto, Utilisateur entity) {
        BeanUtils.copyProperties(dto, entity, "idUtilisateur", "typeUser", "hopital");
    }
}