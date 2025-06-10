package gestiondeshopitauxbackend.WEB;

import gestiondeshopitauxbackend.DTOS.PrefectureDTO;
import gestiondeshopitauxbackend.SERVICES.PrefectureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prefectures")
@RequiredArgsConstructor
@Tag(name = "Préfectures", description = "API pour gérer les préfectures")
public class PrefectureController {

    private final PrefectureService prefectureService;

    @Operation(summary = "Créer une préfecture")
    @PostMapping
    public ResponseEntity<PrefectureDTO> createPrefecture(@RequestBody PrefectureDTO prefectureDTO) {
        return new ResponseEntity<>(prefectureService.createPrefecture(prefectureDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Récupérer une préfecture par ID")
    @GetMapping("/{id}")
    public ResponseEntity<PrefectureDTO> getPrefectureById(@PathVariable Long id) {
        return ResponseEntity.ok(prefectureService.getPrefectureById(id));
    }

    @Operation(summary = "Lister toutes les préfectures")
    @GetMapping
    public ResponseEntity<List<PrefectureDTO>> getAllPrefectures() {
        return ResponseEntity.ok(prefectureService.getAllPrefectures());
    }

    @Operation(summary = "Lister les préfectures par région")
    @GetMapping("/region/{regionId}")
    public ResponseEntity<List<PrefectureDTO>> getPrefecturesByRegion(@PathVariable Long regionId) {
        return ResponseEntity.ok(prefectureService.getPrefecturesByRegion(regionId));
    }

    @Operation(summary = "Mettre à jour une préfecture")
    @PutMapping("/{id}")
    public ResponseEntity<PrefectureDTO> updatePrefecture(@PathVariable Long id, @RequestBody PrefectureDTO prefectureDTO) {
        return ResponseEntity.ok(prefectureService.updatePrefecture(id, prefectureDTO));
    }

    @Operation(summary = "Supprimer une préfecture")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrefecture(@PathVariable Long id) {
        prefectureService.deletePrefecture(id);
        return ResponseEntity.noContent().build();
    }
}