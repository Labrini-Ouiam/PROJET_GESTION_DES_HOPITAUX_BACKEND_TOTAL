package gestiondeshopitauxbackend.WEB;

import gestiondeshopitauxbackend.DTOS.DossierMedicalDTO;
import gestiondeshopitauxbackend.SERVICES.DossierMedicalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/dossiers")
public class DossierMedicalController {

    private final DossierMedicalService dossierService;

    public DossierMedicalController(DossierMedicalService dossierService) {
        this.dossierService = dossierService;
    }

    @PostMapping
    public ResponseEntity<DossierMedicalDTO> createDossier(@RequestBody DossierMedicalDTO dossierDTO) {
        return ResponseEntity.ok(dossierService.createDossier(dossierDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DossierMedicalDTO> getDossierById(@PathVariable Long id) {
        return ResponseEntity.ok(dossierService.getDossierById(id));
    }

    @GetMapping
    public ResponseEntity<List<DossierMedicalDTO>> getAllDossiers() {
        return ResponseEntity.ok(dossierService.getAllDossiers());
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<DossierMedicalDTO>> getDossiersByPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(dossierService.getDossiersByPatient(patientId));
    }

    @PostMapping("/{dossierId}/fichiers")
    public ResponseEntity<DossierMedicalDTO> uploadFichier(
            @PathVariable Long dossierId,
            @RequestParam("file") MultipartFile file,
            @RequestParam Long categorieId,
            @RequestParam Long typeFichierId) {
        return ResponseEntity.ok(dossierService.addFichierToDossier(dossierId, file, categorieId, typeFichierId));
    }

    @GetMapping("/fichiers/{fichierId}/content")
    public ResponseEntity<byte[]> downloadFichier(@PathVariable Long fichierId) {
        return ResponseEntity.ok(dossierService.getFichierContent(fichierId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DossierMedicalDTO> updateDossier(@PathVariable Long id, @RequestBody DossierMedicalDTO dossierDTO) {
        return ResponseEntity.ok(dossierService.updateDossier(id, dossierDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDossier(@PathVariable Long id) {
        dossierService.deleteDossier(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<DossierMedicalDTO>> searchDossiers(@RequestParam String keyword) {
        return ResponseEntity.ok(dossierService.searchDossiers(keyword));
    }
}