package gestiondeshopitauxbackend.WEB;

import gestiondeshopitauxbackend.DTOS.DiagnosticDTO;
import gestiondeshopitauxbackend.SERVICES.DiagnosticService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diagnostics")
public class DiagnosticController {

    private final DiagnosticService diagnosticService;

    public DiagnosticController(DiagnosticService diagnosticService) {
        this.diagnosticService = diagnosticService;
    }

    @PostMapping
    public ResponseEntity<DiagnosticDTO> createDiagnostic(@RequestBody DiagnosticDTO diagnosticDTO) {
        return ResponseEntity.ok(diagnosticService.createDiagnostic(diagnosticDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiagnosticDTO> getDiagnosticById(@PathVariable Long id) {
        return ResponseEntity.ok(diagnosticService.getDiagnosticById(id));
    }

    @GetMapping("/dossier/{dossierId}")
    public ResponseEntity<List<DiagnosticDTO>> getDiagnosticsByDossier(@PathVariable Long dossierId) {
        return ResponseEntity.ok(diagnosticService.getDiagnosticsByDossier(dossierId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiagnosticDTO> updateDiagnostic(@PathVariable Long id, @RequestBody DiagnosticDTO diagnosticDTO) {
        return ResponseEntity.ok(diagnosticService.updateDiagnostic(id, diagnosticDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiagnostic(@PathVariable Long id) {
        diagnosticService.deleteDiagnostic(id);
        return ResponseEntity.noContent().build();
    }
}