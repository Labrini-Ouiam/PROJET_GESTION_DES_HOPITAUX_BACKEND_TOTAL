package gestiondeshopitauxbackend.WEB;

import gestiondeshopitauxbackend.DTOS.PrescriptionMedicamentDetailDTO;
import gestiondeshopitauxbackend.SERVICES.PrescriptionMedicamentDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/*
@RestController
@RequestMapping("/prescriptions-details")
public class PrescriptionMedicamentDetailController {

    private final PrescriptionMedicamentDetailService detailService;

    public PrescriptionMedicamentDetailController(PrescriptionMedicamentDetailService detailService) {
        this.detailService = detailService;
    }

    @PostMapping
    public ResponseEntity<PrescriptionMedicamentDetailDTO> createDetail(@RequestBody PrescriptionMedicamentDetailDTO detailDTO) {
        return ResponseEntity.ok(detailService.createDetail(detailDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrescriptionMedicamentDetailDTO> getDetailById(@PathVariable Long id) {
        return ResponseEntity.ok(detailService.getDetailById(id));
    }

    @GetMapping("/prescription/{prescriptionId}")
    public ResponseEntity<List<PrescriptionMedicamentDetailDTO>> getDetailsByPrescription(@PathVariable Long prescriptionId) {
        return ResponseEntity.ok(detailService.getDetailsByPrescription(prescriptionId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrescriptionMedicamentDetailDTO> updateDetail(@PathVariable Long id, @RequestBody PrescriptionMedicamentDetailDTO detailDTO) {
        return ResponseEntity.ok(detailService.updateDetail(id, detailDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetail(@PathVariable Long id) {
        detailService.deleteDetail(id);
        return ResponseEntity.noContent().build();
    }
}*/
@CrossOrigin
@RestController
@RequestMapping("/prescriptions-details")
public class PrescriptionMedicamentDetailController {

    private final PrescriptionMedicamentDetailService detailService;

    public PrescriptionMedicamentDetailController(PrescriptionMedicamentDetailService detailService) {
        this.detailService = detailService;
    }

    @PostMapping
    public ResponseEntity<PrescriptionMedicamentDetailDTO> createDetail(@RequestBody PrescriptionMedicamentDetailDTO detailDTO) {
        return ResponseEntity.ok(detailService.createDetail(detailDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrescriptionMedicamentDetailDTO> getDetailById(@PathVariable Long id) {
        return ResponseEntity.ok(detailService.getDetailById(id));
    }

    @GetMapping("/prescription/{prescriptionId}")
    public ResponseEntity<List<PrescriptionMedicamentDetailDTO>> getDetailsByPrescription(@PathVariable Long prescriptionId) {
        return ResponseEntity.ok(detailService.getDetailsByPrescription(prescriptionId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrescriptionMedicamentDetailDTO> updateDetail(@PathVariable Long id, @RequestBody PrescriptionMedicamentDetailDTO detailDTO) {
        return ResponseEntity.ok(detailService.updateDetail(id, detailDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetail(@PathVariable Long id) {
        detailService.deleteDetail(id);
        return ResponseEntity.noContent().build();
    }
}