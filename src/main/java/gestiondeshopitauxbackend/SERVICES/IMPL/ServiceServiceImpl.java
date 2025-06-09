package gestiondeshopitauxbackend.SERVICES.IMPL;

import gestiondeshopitauxbackend.DTOS.*;
import gestiondeshopitauxbackend.ENTITIES.*;
import gestiondeshopitauxbackend.MAPPERS.ServiceMapper;
import gestiondeshopitauxbackend.REPOSITORIES.*;
import gestiondeshopitauxbackend.SERVICES.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;
    private final HopitalRepository hopitalRepository;
    private final ServiceMapper mapper;

    @Override
    public ServiceDTO getService(Long id) {
        ServiceEntity service = (ServiceEntity) serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));
        return mapper.fromService(service);
    }

    @Override
    public List<ServiceDTO> listServices() {
        return serviceRepository.findAll()
                .stream()
                .map(mapper::fromService)
                .toList();
    }

    @Override
    public ServiceDTO saveService(ServiceDTO dto) {
        Hopital hopital = hopitalRepository.findById(dto.getHopital().getIdHopital())
                .orElseThrow(() -> new RuntimeException("Hôpital non trouvé"));

        ServiceEntity entity = mapper.toService(dto, hopital);
        ServiceEntity saved = serviceRepository.save(entity);

        return mapper.fromService(saved);
    }

    @Override
    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }

    @Override
    public ServiceDTO updateService(ServiceDTO dto) {
        ServiceEntity service = (ServiceEntity) serviceRepository.findById(dto.getIdService())
                .orElseThrow(() -> new RuntimeException("Service non trouvé"));

        Hopital hopital = hopitalRepository.findById(dto.getHopital().getIdHopital())
                .orElseThrow(() -> new RuntimeException("Hôpital non trouvé"));

        ((ServiceEntity) service).setNom(dto.getNom());
        ((ServiceEntity) service).setDescription(dto.getDescription());
        ((ServiceEntity) service).setHopital(hopital);

        ServiceEntity updated = serviceRepository.save(service);
        return mapper.fromService((ServiceEntity) updated);
    }

}
