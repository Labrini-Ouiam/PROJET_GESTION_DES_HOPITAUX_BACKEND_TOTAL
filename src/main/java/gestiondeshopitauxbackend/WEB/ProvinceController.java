package gestiondeshopitauxbackend.WEB;

import gestiondeshopitauxbackend.DTOS.ProvinceDTO;
import gestiondeshopitauxbackend.SERVICES.ProvinceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provinces")
@RequiredArgsConstructor
@Tag(name = "Provinces", description = "API pour gérer les provinces")
public class ProvinceController {

    private final ProvinceService provinceService;

    @Operation(summary = "Créer une province")
    @PostMapping
    public ResponseEntity<ProvinceDTO> createProvince(@RequestBody ProvinceDTO provinceDTO) {
        return new ResponseEntity<>(provinceService.createProvince(provinceDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Récupérer une province par ID")
    @GetMapping("/{id}")
    public ResponseEntity<ProvinceDTO> getProvinceById(@PathVariable Long id) {
        return ResponseEntity.ok(provinceService.getProvinceById(id));
    }

    @Operation(summary = "Lister toutes les provinces")
    @GetMapping
    public ResponseEntity<List<ProvinceDTO>> getAllProvinces() {
        return ResponseEntity.ok(provinceService.getAllProvinces());
    }

    @Operation(summary = "Lister les provinces par région")
    @GetMapping("/region/{regionId}")
    public ResponseEntity<List<ProvinceDTO>> getProvincesByRegion(@PathVariable Long regionId) {
        return ResponseEntity.ok(provinceService.getProvincesByRegion(regionId));
    }

    @Operation(summary = "Mettre à jour une province")
    @PutMapping("/{id}")
    public ResponseEntity<ProvinceDTO> updateProvince(@PathVariable Long id, @RequestBody ProvinceDTO provinceDTO) {
        return ResponseEntity.ok(provinceService.updateProvince(id, provinceDTO));
    }

    @Operation(summary = "Supprimer une province")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProvince(@PathVariable Long id) {
        provinceService.deleteProvince(id);
        return ResponseEntity.noContent().build();
    }
}