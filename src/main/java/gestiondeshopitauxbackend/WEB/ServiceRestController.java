package gestiondeshopitauxbackend.WEB;

import gestiondeshopitauxbackend.DTOS.ServiceDTO;
import gestiondeshopitauxbackend.SERVICES.ServiceService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
@RequiredArgsConstructor
public class ServiceRestController {

    private final ServiceService serviceService;

    @GetMapping("/{id}")
    public ResponseEntity<ServiceDTO> getService(@PathVariable Long id) {
        return ResponseEntity.ok(serviceService.getService(id));
    }

    @GetMapping
    public ResponseEntity<List<ServiceDTO>> listServices() {
        return ResponseEntity.ok(serviceService.listServices());
    }

    @PostMapping
    public ResponseEntity<ServiceDTO> createService(@RequestBody ServiceDTO dto) {
        return new ResponseEntity<>(serviceService.saveService(dto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        serviceService.deleteService(id);
        return ResponseEntity.noContent().build();
    }
}
