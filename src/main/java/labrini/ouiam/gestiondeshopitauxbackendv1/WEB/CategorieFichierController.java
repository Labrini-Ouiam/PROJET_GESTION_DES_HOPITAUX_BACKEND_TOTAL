package labrini.ouiam.gestiondeshopitauxbackendv1.WEB;

import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.CategorieFichierDTO;
import labrini.ouiam.gestiondeshopitauxbackendv1.SERVICES.CategorieFichierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories-fichier")
public class CategorieFichierController {

    private final CategorieFichierService categorieService;

    public CategorieFichierController(CategorieFichierService categorieService) {
        this.categorieService = categorieService;
    }

    @PostMapping
    public ResponseEntity<CategorieFichierDTO> createCategorie(@RequestBody CategorieFichierDTO categorieDTO) {
        return ResponseEntity.ok(categorieService.createCategorie(categorieDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategorieFichierDTO> getCategorieById(@PathVariable Long id) {
        return ResponseEntity.ok(categorieService.getCategorieById(id));
    }

    @GetMapping
    public ResponseEntity<List<CategorieFichierDTO>> getAllCategories() {
        return ResponseEntity.ok(categorieService.getAllCategories());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategorieFichierDTO> updateCategorie(@PathVariable Long id, @RequestBody CategorieFichierDTO categorieDTO) {
        return ResponseEntity.ok(categorieService.updateCategorie(id, categorieDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategorie(@PathVariable Long id) {
        categorieService.deleteCategorie(id);
        return ResponseEntity.noContent().build();
    }
}