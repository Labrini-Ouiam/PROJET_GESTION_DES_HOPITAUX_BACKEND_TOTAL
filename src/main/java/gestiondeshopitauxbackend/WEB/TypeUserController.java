package gestiondeshopitauxbackend.WEB;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import gestiondeshopitauxbackend.DTOS.TypeUserDTO;
import gestiondeshopitauxbackend.SERVICES.TypeUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Type d'Utilisateur", description = "Gestion des types d'utilisateurs")
@RestController
@RequestMapping("/types-utilisateurs")
public class TypeUserController {

    private final TypeUserService typeUserService;

    public TypeUserController(TypeUserService typeUserService) {
        this.typeUserService = typeUserService;
    }

    @Operation(summary = "Créer un type d'utilisateur")
    @PostMapping
    public ResponseEntity<TypeUserDTO> createTypeUser(@RequestBody TypeUserDTO typeUserDTO) {
        return ResponseEntity.ok(typeUserService.createTypeUser(typeUserDTO));
    }

    @Operation(summary = "Récupérer un type d'utilisateur par ID")
    @GetMapping("/{id}")
    public ResponseEntity<TypeUserDTO> getTypeUserById(@PathVariable Long id) {
        return ResponseEntity.ok(typeUserService.getTypeUserById(id));
    }

    @Operation(summary = "Lister tous les types d'utilisateurs")
    @GetMapping
    public ResponseEntity<List<TypeUserDTO>> getAllTypeUsers() {
        return ResponseEntity.ok(typeUserService.getAllTypeUsers());
    }

    @Operation(summary = "Mettre à jour un type d'utilisateur")
    @PutMapping("/{id}")
    public ResponseEntity<TypeUserDTO> updateTypeUser(@PathVariable Long id, @RequestBody TypeUserDTO typeUserDTO) {
        return ResponseEntity.ok(typeUserService.updateTypeUser(id, typeUserDTO));
    }

    @Operation(summary = "Supprimer un type d'utilisateur")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTypeUser(@PathVariable Long id) {
        typeUserService.deleteTypeUser(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Trouver un type d'utilisateur par rôle")
    @GetMapping("/role/{role}")
    public ResponseEntity<TypeUserDTO> findByRole(@PathVariable String role) {
        TypeUserDTO typeUserDTO = typeUserService.findByRole(role);
        if (typeUserDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(typeUserDTO);
    }
}