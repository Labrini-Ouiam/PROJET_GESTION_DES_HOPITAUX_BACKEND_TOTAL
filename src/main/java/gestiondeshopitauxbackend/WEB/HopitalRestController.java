package gestiondeshopitauxbackend.WEB;

import gestiondeshopitauxbackend.DTOS.HopitalDTO;
import gestiondeshopitauxbackend.SERVICES.HopitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hopitaux")
@RequiredArgsConstructor
public class HopitalRestController {

    private final HopitalService hopitalService;

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
}
