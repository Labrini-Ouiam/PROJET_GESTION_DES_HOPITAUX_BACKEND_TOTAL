package labrini.ouiam.gestiondeshopitauxbackendv1.WEB;

import java.util.Date;
import java.util.List;
import java.util.Map;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.RendezVousDTO;
import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.RendezVousRequestDTO;
import labrini.ouiam.gestiondeshopitauxbackendv1.SERVICES.RendezVousService;

@RestController
@RequestMapping("/api/rendez-vous")
@CrossOrigin(origins = "*")
public class RendezVousController {

    @Autowired
    private RendezVousService rendezVousService;
    
    @GetMapping
    public ResponseEntity<List<RendezVousDTO>> getAllRendezVous() {
        return ResponseEntity.ok(rendezVousService.getAllRendezVous());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<RendezVousDTO> getRendezVousById(@PathVariable Long id) {
        return ResponseEntity.ok(rendezVousService.getRendezVousById(id));
    }
    
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<RendezVousDTO>> getRendezVousByPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(rendezVousService.getRendezVousByPatient(patientId));
    }
    
    @GetMapping("/patient/{patientId}/between")
    public ResponseEntity<List<RendezVousDTO>> getRendezVousByPatientAndDateRange(
            @PathVariable Long patientId, 
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date debut,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fin) {
        return ResponseEntity.ok(rendezVousService.getRendezVousByPatientAndDateRange(patientId, debut, fin));
    }
    
    @PostMapping("/patient/{patientId}")
    public ResponseEntity<RendezVousDTO> createRendezVous(
            @Valid @RequestBody RendezVousRequestDTO rendezVousRequestDTO,
            @PathVariable Long patientId) {
        return new ResponseEntity<>(rendezVousService.saveRendezVous(rendezVousRequestDTO, patientId), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<RendezVousDTO> updateRendezVous(
            @PathVariable Long id, 
            @Valid @RequestBody RendezVousRequestDTO rendezVousRequestDTO) {
        return ResponseEntity.ok(rendezVousService.updateRendezVous(id, rendezVousRequestDTO));
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<RendezVousDTO> updateRendezVousStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> status) {
        return ResponseEntity.ok(rendezVousService.updateRendezVousStatus(id, status.get("statut")));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRendezVous(@PathVariable Long id) {
        rendezVousService.deleteRendezVous(id);
        return ResponseEntity.noContent().build();
    }
}
