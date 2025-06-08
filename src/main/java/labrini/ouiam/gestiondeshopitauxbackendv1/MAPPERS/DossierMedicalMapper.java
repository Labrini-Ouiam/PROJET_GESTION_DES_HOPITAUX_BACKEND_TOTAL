package labrini.ouiam.gestiondeshopitauxbackendv1.MAPPERS;

import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.DossierMedicalDTO;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.DossierMedical;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class DossierMedicalMapper {
    public DossierMedicalDTO toDto(DossierMedical entity) {
        DossierMedicalDTO dto = new DossierMedicalDTO();
        BeanUtils.copyProperties(entity, dto);
        dto.setIdDossierMedical(entity.getIdDossierMedical());
        if (entity.getPatient() != null) {
            dto.setPatientId(entity.getPatient().getId());
            dto.setPatientNom(entity.getPatient().getNom());
            dto.setPatientPrenom(entity.getPatient().getPrenom());
            dto.setDiagnostics(entity.getDiagnostics());
            dto.setFichiers(entity.getFichiers());
            dto.setResultats(entity.getResultats());
            dto.setInterventions(entity.getInterventions());
            dto.setPrescriptions(entity.getPrescriptions());
        }
        return dto;
    }

    public DossierMedical toEntity(DossierMedicalDTO dto) {
        DossierMedical entity = new DossierMedical();
        BeanUtils.copyProperties(dto, entity);
        entity.setIdDossierMedical(dto.getIdDossierMedical());
        return entity;
    }
}