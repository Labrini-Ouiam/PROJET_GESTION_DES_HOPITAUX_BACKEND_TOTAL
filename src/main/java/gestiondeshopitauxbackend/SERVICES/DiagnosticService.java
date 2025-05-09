package gestiondeshopitauxbackend.SERVICES;

import gestiondeshopitauxbackend.DTOS.DiagnosticDTO;
import java.util.List;

public interface DiagnosticService {
    DiagnosticDTO createDiagnostic(DiagnosticDTO diagnosticDTO);
    DiagnosticDTO getDiagnosticById(Long id);
    List<DiagnosticDTO> getDiagnosticsByDossier(Long dossierId);
    DiagnosticDTO updateDiagnostic(Long id, DiagnosticDTO diagnosticDTO);
    void deleteDiagnostic(Long id);
}