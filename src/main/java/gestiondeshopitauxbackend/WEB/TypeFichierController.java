package gestiondeshopitauxbackend.WEB;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import gestiondeshopitauxbackend.DTOS.TypeFichierDTO;
import gestiondeshopitauxbackend.SERVICES.TypeFichierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@Tag(name = "Type de Fichier", description = "Gestion des types de fichiers")
@RestController
@RequestMapping("/types-fichiers")
public class TypeFichierController {

    private final TypeFichierService typeFichierService;

    public TypeFichierController(TypeFichierService typeFichierService) {
        this.typeFichierService = typeFichierService;
    }

    @Operation(summary = "Créer un type de fichier")
    @PostMapping
    public ResponseEntity<TypeFichierDTO> createTypeFichier(@RequestBody TypeFichierDTO typeFichierDTO) {
        return ResponseEntity.ok(typeFichierService.createTypeFichier(typeFichierDTO));
    }

    @Operation(summary = "Récupérer un type de fichier par ID")
    @GetMapping("/{id}")
    public ResponseEntity<TypeFichierDTO> getTypeFichierById(@PathVariable Long id) {
        return ResponseEntity.ok(typeFichierService.getTypeFichierById(id));
    }

    @Operation(summary = "Lister tous les types de fichiers")
    @GetMapping
    public ResponseEntity<List<TypeFichierDTO>> getAllTypeFichiers() {
        return ResponseEntity.ok(typeFichierService.getAllTypeFichiers());
    }

    @Operation(summary = "Mettre à jour un type de fichier")
    @PutMapping("/{id}")
    public ResponseEntity<TypeFichierDTO> updateTypeFichier(@PathVariable Long id, @RequestBody TypeFichierDTO typeFichierDTO) {
        return ResponseEntity.ok(typeFichierService.updateTypeFichier(id, typeFichierDTO));
    }

    @Operation(summary = "Supprimer un type de fichier")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTypeFichier(@PathVariable Long id) {
        typeFichierService.deleteTypeFichier(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Trouver un type de fichier par nom")
    @GetMapping("/nom/{nom}")
    public ResponseEntity<TypeFichierDTO> findByNom(@PathVariable String nom) {
        TypeFichierDTO typeFichierDTO = typeFichierService.findByNom(nom);
        if (typeFichierDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(typeFichierDTO);
    }
}