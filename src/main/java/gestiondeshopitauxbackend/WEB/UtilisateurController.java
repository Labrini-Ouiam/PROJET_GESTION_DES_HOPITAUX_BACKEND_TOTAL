package gestiondeshopitauxbackend.WEB;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import gestiondeshopitauxbackend.DTOS.UtilisateurDTO;
import gestiondeshopitauxbackend.SERVICES.UtilisateurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@Tag(name = "Utilisateurs", description = "Gestion des utilisateurs du système")
@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {

        private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @Operation(summary = "Créer un utilisateur")
    @ApiResponse(responseCode = "201", description = "Utilisateur créé")

    @PostMapping
    public ResponseEntity<UtilisateurDTO> createUser(
            @RequestBody @Schema(description = "DTO de l'utilisateur") UtilisateurDTO utilisateurDTO) {
        return ResponseEntity.ok(utilisateurService.createUser(utilisateurDTO));
    }

    @Operation(summary = "Récupérer un utilisateur par ID")
    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurDTO> getUserById(
            @Parameter(description = "ID de l'utilisateur") @PathVariable Long id) {
        return ResponseEntity.ok(utilisateurService.getUserById(id));
    }

    @Operation(summary = "Lister tous les utilisateurs")
    @GetMapping
    public ResponseEntity<List<UtilisateurDTO>> getAllUsers() {
        return ResponseEntity.ok(utilisateurService.getAllUsers());
    }

    @Operation(summary = "Mettre à jour un utilisateur")
    @PutMapping("/{id}")
    public ResponseEntity<UtilisateurDTO> updateUser(
            @Parameter(description = "ID de l'utilisateur") @PathVariable Long id,
            @RequestBody @Schema(description = "Données mises à jour") UtilisateurDTO utilisateurDTO) {
        return ResponseEntity.ok(utilisateurService.updateUser(id, utilisateurDTO));
    }

    @Operation(summary = "Supprimer un utilisateur")
    @ApiResponse(responseCode = "204", description = "Utilisateur supprimé")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "ID de l'utilisateur") @PathVariable Long id) {
        utilisateurService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Lister les médecins d'un hôpital")
    @GetMapping("/hopital/{hopitalId}")
    public ResponseEntity<List<UtilisateurDTO>> getMedecinsByHopital(
            @Parameter(description = "ID de l'hôpital") @PathVariable Long hopitalId) {
        return ResponseEntity.ok(utilisateurService.getMedecinsByHopital(hopitalId));
    }

    @Operation(summary = "Rechercher des utilisateurs")
    @GetMapping("/search")
    public ResponseEntity<List<UtilisateurDTO>> searchUsers(
            @Parameter(description = "Mot-clé de recherche") @RequestParam String keyword) {
        return ResponseEntity.ok(utilisateurService.searchUsers(keyword));
    }
}
