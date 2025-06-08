package labrini.ouiam.gestiondeshopitauxbackendv1.WEB;

import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.PrescriptionMedicalDTO;
import labrini.ouiam.gestiondeshopitauxbackendv1.SERVICES.PrescriptionMedicalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/*
@RestController
@RequestMapping("/prescriptions")
public class PrescriptionMedicalController {

    private final PrescriptionMedicalService prescriptionService;

    public PrescriptionMedicalController(PrescriptionMedicalService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @PostMapping
    public ResponseEntity<PrescriptionMedicalDTO> createPrescription(@RequestBody PrescriptionMedicalDTO prescriptionDTO) {
        return ResponseEntity.ok(prescriptionService.createPrescription(prescriptionDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrescriptionMedicalDTO> getPrescriptionById(@PathVariable Long id) {
        return ResponseEntity.ok(prescriptionService.getPrescriptionById(id));
    }

    @GetMapping("/dossier/{dossierId}")
    public ResponseEntity<List<PrescriptionMedicalDTO>> getPrescriptionsByDossier(@PathVariable Long dossierId) {
        return ResponseEntity.ok(prescriptionService.getPrescriptionsByDossier(dossierId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrescriptionMedicalDTO> updatePrescription(@PathVariable Long id, @RequestBody PrescriptionMedicalDTO prescriptionDTO) {
        return ResponseEntity.ok(prescriptionService.updatePrescription(id, prescriptionDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrescription(@PathVariable Long id) {
        prescriptionService.deletePrescription(id);
        return ResponseEntity.noContent().build();
    }
}*/

@RestController
@RequestMapping("/prescriptions")
public class PrescriptionMedicalController {

    private final PrescriptionMedicalService prescriptionService;

    public PrescriptionMedicalController(PrescriptionMedicalService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @PostMapping
    public ResponseEntity<PrescriptionMedicalDTO> createPrescription(@RequestBody PrescriptionMedicalDTO prescriptionDTO) {
        return ResponseEntity.ok(prescriptionService.createPrescription(prescriptionDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrescriptionMedicalDTO> getPrescriptionById(@PathVariable Long id) {
        return ResponseEntity.ok(prescriptionService.getPrescriptionById(id));
    }

    @GetMapping("/dossier/{dossierId}")
    public ResponseEntity<List<PrescriptionMedicalDTO>> getPrescriptionsByDossier(@PathVariable Long dossierId) {
        return ResponseEntity.ok(prescriptionService.getPrescriptionsByDossier(dossierId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrescriptionMedicalDTO> updatePrescription(@PathVariable Long id, @RequestBody PrescriptionMedicalDTO prescriptionDTO) {
        return ResponseEntity.ok(prescriptionService.updatePrescription(id, prescriptionDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrescription(@PathVariable Long id) {
        prescriptionService.deletePrescription(id);
        return ResponseEntity.noContent().build();
    }
}