package labrini.ouiam.gestiondeshopitauxbackendv1.WEB;

import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.ResultatExamenDTO;
import labrini.ouiam.gestiondeshopitauxbackendv1.SERVICES.ResultatExamenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resultats-examen")
public class ResultatExamenController {

    private final ResultatExamenService resultatService;

    public ResultatExamenController(ResultatExamenService resultatService) {
        this.resultatService = resultatService;
    }

    @PostMapping
    public ResponseEntity<ResultatExamenDTO> createResultat(@RequestBody ResultatExamenDTO resultatDTO) {
        return ResponseEntity.ok(resultatService.createResultat(resultatDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultatExamenDTO> getResultatById(@PathVariable Long id) {
        return ResponseEntity.ok(resultatService.getResultatById(id));
    }

    @GetMapping("/dossier/{dossierId}")
    public ResponseEntity<List<ResultatExamenDTO>> getResultatsByDossier(@PathVariable Long dossierId) {
        return ResponseEntity.ok(resultatService.getResultatsByDossier(dossierId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResultatExamenDTO> updateResultat(@PathVariable Long id, @RequestBody ResultatExamenDTO resultatDTO) {
        return ResponseEntity.ok(resultatService.updateResultat(id, resultatDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResultat(@PathVariable Long id) {
        resultatService.deleteResultat(id);
        return ResponseEntity.noContent().build();
    }
}