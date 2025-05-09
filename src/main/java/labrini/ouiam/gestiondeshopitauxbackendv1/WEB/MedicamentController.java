package labrini.ouiam.gestiondeshopitauxbackendv1.WEB;

import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.MedicamentDTO;
import labrini.ouiam.gestiondeshopitauxbackendv1.SERVICES.MedicamentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicaments")
public class MedicamentController {

    private final MedicamentService medicamentService;

    public MedicamentController(MedicamentService medicamentService) {
        this.medicamentService = medicamentService;
    }

    @PostMapping
    public ResponseEntity<MedicamentDTO> createMedicament(@RequestBody MedicamentDTO medicamentDTO) {
        return ResponseEntity.ok(medicamentService.createMedicament(medicamentDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicamentDTO> getMedicamentById(@PathVariable Long id) {
        return ResponseEntity.ok(medicamentService.getMedicamentById(id));
    }

    @GetMapping
    public ResponseEntity<List<MedicamentDTO>> getAllMedicaments() {
        return ResponseEntity.ok(medicamentService.getAllMedicaments());
    }

    @GetMapping("/search")
    public ResponseEntity<List<MedicamentDTO>> searchMedicaments(@RequestParam String keyword) {
        return ResponseEntity.ok(medicamentService.searchMedicaments(keyword));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicamentDTO> updateMedicament(@PathVariable Long id, @RequestBody MedicamentDTO medicamentDTO) {
        return ResponseEntity.ok(medicamentService.updateMedicament(id, medicamentDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicament(@PathVariable Long id) {
        medicamentService.deleteMedicament(id);
        return ResponseEntity.noContent().build();
    }
}