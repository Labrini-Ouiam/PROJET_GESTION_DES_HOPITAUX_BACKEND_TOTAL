package gestiondeshopitauxbackend.WEB;

import gestiondeshopitauxbackend.DTOS.ServiceDTO;
import gestiondeshopitauxbackend.DTOS.SousServiceDTO;
import gestiondeshopitauxbackend.SERVICES.ServiceService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin
@RestController
@RequestMapping("/services")
@RequiredArgsConstructor
public class ServiceRestController {

    private static final Logger logger = LoggerFactory.getLogger(ServiceRestController.class);
    private final ServiceService serviceService;

    @GetMapping("/{id}")
    public ResponseEntity<ServiceDTO> getService(@PathVariable Long id) {
        return ResponseEntity.ok(serviceService.getService(id));
    }

    @GetMapping
    public ResponseEntity<List<ServiceDTO>> listServices() {
        try {
            logger.info("Récupération de la liste des services");
            List<ServiceDTO> services = serviceService.listServices();
            logger.info("Nombre de services récupérés : {}", services.size());
            return ResponseEntity.ok(services);
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération des services", e);
            throw e;
        }
    }

    @PostMapping
    public ResponseEntity<ServiceDTO> createService(@RequestBody ServiceDTO dto) {
        return new ResponseEntity<>(serviceService.saveService(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateService(@PathVariable Long id, @RequestBody ServiceDTO dto) {
        try {
            dto.setIdService(id); // s'assurer que l'ID est bien mis à jour
            ServiceDTO updated = serviceService.updateService(dto);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("modifiée par un autre utilisateur")) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(Map.of("message", e.getMessage()));
            }
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        serviceService.deleteService(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Récupérer les services d'un hôpital")
    @GetMapping("/hopital/{hopitalId}")
    public ResponseEntity<List<ServiceDTO>> getServicesByHopital(@PathVariable Long hopitalId) {
        try {
            logger.info("Récupération des services pour l'hôpital ID: {}", hopitalId);
            List<ServiceDTO> services = serviceService.getServicesByHopital(hopitalId);
            logger.info("Nombre de services récupérés: {}", services.size());
            return ResponseEntity.ok(services);
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération des services pour l'hôpital ID: {}", hopitalId, e);
            throw e;
        }
    }

    @Operation(summary = "Récupérer les sous-services d'un service")
    @GetMapping("/{serviceId}/sous-services")
    public ResponseEntity<List<SousServiceDTO>> getSousServicesByService(@PathVariable Long serviceId) {
        return ResponseEntity.ok(serviceService.getSousServicesByService(serviceId));
    }
}
