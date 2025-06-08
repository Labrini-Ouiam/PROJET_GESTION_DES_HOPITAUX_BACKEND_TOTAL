package labrini.ouiam.gestiondeshopitauxbackendv1.MAPPERS;

import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.DiagnosticDTO;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.Diagnostic;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class DiagnosticMapper {
    public DiagnosticDTO toDto(Diagnostic entity) {
        DiagnosticDTO dto = new DiagnosticDTO();
        BeanUtils.copyProperties(entity, dto);
        dto.setIdDiagnostic(entity.getIdDiagnostic());
        dto.setDossierMedicalId(entity.getDossierMedical().getIdDossierMedical());
        return dto;
    }

    public Diagnostic toEntity(DiagnosticDTO dto) {
        Diagnostic entity = new Diagnostic();
        BeanUtils.copyProperties(dto, entity);
        entity.setIdDiagnostic(dto.getIdDiagnostic());
        return entity;
    }
}