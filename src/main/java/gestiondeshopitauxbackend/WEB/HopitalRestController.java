package gestiondeshopitauxbackend.WEB;

import gestiondeshopitauxbackend.DTOS.HopitalDTO;
import gestiondeshopitauxbackend.DTOS.ServiceDTO;
import gestiondeshopitauxbackend.DTOS.SousServiceDTO;
import gestiondeshopitauxbackend.SERVICES.HopitalService;
import gestiondeshopitauxbackend.SERVICES.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("hopitaux")
@RequiredArgsConstructor
public class HopitalRestController {

    private final HopitalService hopitalService;
    private final ServiceService serviceService;

    @PostMapping
    public HopitalDTO create(@RequestBody HopitalDTO dto) {
        return hopitalService.createHopital(dto);
    }

    @GetMapping
    public List<HopitalDTO> getAll() {
        return hopitalService.getAllHopitaux();
    }

    @GetMapping("/{id}")
    public HopitalDTO getById(@PathVariable Long id) {
        return hopitalService.getHopitalById(id);
    }

    @PutMapping("/{id}")
    public HopitalDTO update(@PathVariable Long id, @RequestBody HopitalDTO dto) {
        return hopitalService.updateHopital(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        hopitalService.deleteHopital(id);
    }

    @GetMapping("/{hopitalId}/services")
    public ResponseEntity<List<ServiceDTO>> getServicesByHopital(@PathVariable Long hopitalId) {
        return ResponseEntity.ok(serviceService.getServicesByHopital(hopitalId));
    }

    @GetMapping("/{hopitalId}/services/{serviceId}/sous-services")
    public ResponseEntity<List<SousServiceDTO>> getSousServicesByServiceInHopital(
            @PathVariable Long hopitalId, 
            @PathVariable Long serviceId) {
        // Vérifier que le service appartient bien à l'hôpital
        List<ServiceDTO> services = serviceService.getServicesByHopital(hopitalId);
        boolean serviceExistsInHopital = services.stream()
                .anyMatch(s -> s.getIdService().equals(serviceId));
        
        if (!serviceExistsInHopital) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(serviceService.getSousServicesByService(serviceId));
    }
}
