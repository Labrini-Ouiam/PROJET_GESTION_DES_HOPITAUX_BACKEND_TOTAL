package gestiondeshopitauxbackend.WEB;

import gestiondeshopitauxbackend.DTOS.FichierDTO;
import gestiondeshopitauxbackend.SERVICES.FichierService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/*
@RestController
@RequestMapping("/fichiers")
public class FichierController {

    private final FichierService fichierService;

    public FichierController(FichierService fichierService) {
        this.fichierService = fichierService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FichierDTO> getFichierById(@PathVariable Long id) {
        return ResponseEntity.ok(fichierService.getFichierById(id));
    }

    @GetMapping("/dossier/{dossierId}")
    public ResponseEntity<List<FichierDTO>> getFichiersByDossier(@PathVariable Long dossierId) {
        return ResponseEntity.ok(fichierService.getFichiersByDossier(dossierId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FichierDTO> updateFichier(@PathVariable Long id, @RequestBody FichierDTO fichierDTO) {
        return ResponseEntity.ok(fichierService.updateFichier(id, fichierDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFichier(@PathVariable Long id) {
        fichierService.deleteFichier(id);
        return ResponseEntity.noContent().build();
    }
}*/

@RestController
@RequestMapping("/fichiers")
@CrossOrigin
public class FichierController {

    private final FichierService fichierService;

    public FichierController(FichierService fichierService) {
        this.fichierService = fichierService;
    }

    @PostMapping("/upload")
    public ResponseEntity<FichierDTO> uploadFile(
            @RequestParam("dossierId") Long dossierId,
            @RequestParam("file") MultipartFile file,
            @RequestParam("categorieId") Long categorieId,
            @RequestParam("typeFichierId") Long typeFichierId) {

        return ResponseEntity.ok(fichierService.uploadFichier(dossierId, file, categorieId, typeFichierId));
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) {
        byte[] fileContent = fichierService.downloadFichier(id);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"file\"")
                .body(fileContent);
    }

    @GetMapping("/dossier/{dossierId}")
    public ResponseEntity<List<FichierDTO>> getFilesByDossier(@PathVariable Long dossierId) {
        return ResponseEntity.ok(fichierService.getFichiersByDossier(dossierId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFile(@PathVariable Long id) {
        fichierService.deleteFichier(id);
        return ResponseEntity.noContent().build();
    }
}