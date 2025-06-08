package labrini.ouiam.gestiondeshopitauxbackendv1.WEB;

import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.InterventionMedicalDTO;
import labrini.ouiam.gestiondeshopitauxbackendv1.SERVICES.InterventionMedicalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interventions")
public class InterventionMedicalController {

    private final InterventionMedicalService interventionService;

    public InterventionMedicalController(InterventionMedicalService interventionService) {
        this.interventionService = interventionService;
    }

    @PostMapping
    public ResponseEntity<InterventionMedicalDTO> createIntervention(@RequestBody InterventionMedicalDTO interventionDTO) {
        return ResponseEntity.ok(interventionService.createIntervention(interventionDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InterventionMedicalDTO> getInterventionById(@PathVariable Long id) {
        return ResponseEntity.ok(interventionService.getInterventionById(id));
    }

    @GetMapping
    public ResponseEntity<List<InterventionMedicalDTO>> getAllInterventions() {
        return ResponseEntity.ok(interventionService.getAllInterventions());
    }

    @GetMapping("/dossier/{dossierId}")
    public ResponseEntity<List<InterventionMedicalDTO>> getInterventionsByDossier(@PathVariable Long dossierId) {
        return ResponseEntity.ok(interventionService.getInterventionsByDossierMedical(dossierId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InterventionMedicalDTO> updateIntervention(@PathVariable Long id, @RequestBody InterventionMedicalDTO interventionDTO) {
        return ResponseEntity.ok(interventionService.updateIntervention(id, interventionDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIntervention(@PathVariable Long id) {
        interventionService.deleteIntervention(id);
        return ResponseEntity.noContent().build();
    }
}