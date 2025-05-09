package labrini.ouiam.gestiondeshopitauxbackendv1.MAPPERS;

import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.InterventionMedicalDTO;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.InterventionMedical;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class InterventionMapper {
    public InterventionMedicalDTO toDto(InterventionMedical entity){
        InterventionMedicalDTO dto = new InterventionMedicalDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }


    public InterventionMedical toEntity(InterventionMedicalDTO dto){
        InterventionMedical entity = new InterventionMedical();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    public String getMedecinResponsable(InterventionMedical intervention) {
        // Implémentez la logique pour récupérer le médecin responsable
        // Par exemple, à partir de l'utilisateur connecté ou d'une relation
        return "Dr. " + intervention.getDossierMedical().getPatient().getNom();
    }
}