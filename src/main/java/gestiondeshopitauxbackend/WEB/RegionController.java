package gestiondeshopitauxbackend.WEB;

import gestiondeshopitauxbackend.DTOS.RegionDTO;
import gestiondeshopitauxbackend.SERVICES.RegionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/regions")
@RequiredArgsConstructor
@Tag(name = "Régions", description = "API pour gérer les régions")
public class RegionController {

    private final RegionService regionService;

    @Operation(summary = "Créer une région")
    @PostMapping
    public ResponseEntity<RegionDTO> createRegion(@RequestBody RegionDTO regionDTO) {
        return new ResponseEntity<>(regionService.createRegion(regionDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Récupérer une région par ID")
    @GetMapping("/{id}")
    public ResponseEntity<RegionDTO> getRegionById(@PathVariable Long id) {
        return ResponseEntity.ok(regionService.getRegionById(id));
    }

    @Operation(summary = "Lister toutes les régions")
    @GetMapping
    public ResponseEntity<List<RegionDTO>> getAllRegions() {
        return ResponseEntity.ok(regionService.getAllRegions());
    }

    @Operation(summary = "Mettre à jour une région")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRegion(@PathVariable Long id, @RequestBody RegionDTO regionDTO) {
        try {
            return ResponseEntity.ok(regionService.updateRegion(id, regionDTO));
        } catch (RuntimeException e) {
            if (e.getMessage().contains("modifiée par un autre utilisateur")) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(Map.of("message", e.getMessage()));
            }
            throw e;
        }
    }

    @Operation(summary = "Supprimer une région")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegion(@PathVariable Long id) {
        regionService.deleteRegion(id);
        return ResponseEntity.noContent().build();
    }
}