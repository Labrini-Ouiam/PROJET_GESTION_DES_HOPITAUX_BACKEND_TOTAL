package labrini.ouiam.gestiondeshopitauxbackendv1.SERVICES;

import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.DiagnosticDTO;
import java.util.List;

public interface DiagnosticService {
    List<DiagnosticDTO> getAllDiagnostics();
    DiagnosticDTO createDiagnostic(DiagnosticDTO diagnosticDTO);
    DiagnosticDTO getDiagnosticById(Long id);
    List<DiagnosticDTO> getDiagnosticsByDossier(Long dossierId);
    DiagnosticDTO updateDiagnostic(Long id, DiagnosticDTO diagnosticDTO);
    void deleteDiagnostic(Long id);
}