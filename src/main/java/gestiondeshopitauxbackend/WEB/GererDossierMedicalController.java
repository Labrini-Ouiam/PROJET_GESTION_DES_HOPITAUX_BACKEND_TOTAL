package gestiondeshopitauxbackend.WEB;

import gestiondeshopitauxbackend.DTOS.GererDossierMedicalDTO;
import gestiondeshopitauxbackend.SERVICES.GererDossierMedicalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/gestions-dossiers")
//public class GererDossierMedicalController {
//
//    private final GererDossierMedicalService gestionService;
//
//    public GererDossierMedicalController(GererDossierMedicalService gestionService) {
//        this.gestionService = gestionService;
//    }
//
//    @PostMapping
//    public ResponseEntity<GererDossierMedicalDTO> createGestion(@RequestBody GererDossierMedicalDTO gestionDTO) {
//        return ResponseEntity.ok(gestionService.createGestion(gestionDTO));
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<GererDossierMedicalDTO> getGestionById(@PathVariable Long id) {
//        return ResponseEntity.ok(gestionService.getGestionById(id));
//    }
//
//    @GetMapping("/dossier/{dossierId}")
//    public ResponseEntity<List<GererDossierMedicalDTO>> getGestionsByDossier(@PathVariable Long dossierId) {
//        return ResponseEntity.ok(gestionService.getGestionsByDossier(dossierId));
//    }
//
//    @GetMapping("/utilisateur/{utilisateurId}")
//    public ResponseEntity<List<GererDossierMedicalDTO>> getGestionsByUtilisateur(@PathVariable Long utilisateurId) {
//        return ResponseEntity.ok(gestionService.getGestionsByUtilisateur(utilisateurId));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<GererDossierMedicalDTO> updateGestion(@PathVariable Long id, @RequestBody GererDossierMedicalDTO gestionDTO) {
//        return ResponseEntity.ok(gestionService.updateGestion(id, gestionDTO));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteGestion(@PathVariable Long id) {
//        gestionService.deleteGestion(id);
//        return ResponseEntity.noContent().build();
//    }
//}
@RestController
@RequestMapping("/gererdossiers")
public class GererDossierMedicalController {

    private final GererDossierMedicalService gestionService;

    public GererDossierMedicalController(GererDossierMedicalService gestionService) {
        this.gestionService = gestionService;
    }

    @PostMapping
    public ResponseEntity<GererDossierMedicalDTO> createGestion(@RequestBody GererDossierMedicalDTO gestionDTO) {
        return ResponseEntity.ok(gestionService.createGestion(gestionDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GererDossierMedicalDTO> getGestionById(@PathVariable Long id) {
        return ResponseEntity.ok(gestionService.getGestionById(id));
    }

    @GetMapping("/dossier/{dossierId}")
    public ResponseEntity<List<GererDossierMedicalDTO>> getGestionsByDossier(@PathVariable Long dossierId) {
        return ResponseEntity.ok(gestionService.getGestionsByDossier(dossierId));
    }

    @GetMapping("/utilisateur/{utilisateurId}")
    public ResponseEntity<List<GererDossierMedicalDTO>> getGestionsByUtilisateur(@PathVariable Long utilisateurId) {
        return ResponseEntity.ok(gestionService.getGestionsByUtilisateur(utilisateurId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GererDossierMedicalDTO> updateGestion(@PathVariable Long id, @RequestBody GererDossierMedicalDTO gestionDTO) {
        return ResponseEntity.ok(gestionService.updateGestion(id, gestionDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGestion(@PathVariable Long id) {
        gestionService.deleteGestion(id);
        return ResponseEntity.noContent().build();
    }
}