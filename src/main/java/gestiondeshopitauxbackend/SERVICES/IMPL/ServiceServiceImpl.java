package gestiondeshopitauxbackend.SERVICES.IMPL;

import gestiondeshopitauxbackend.DTOS.*;
import gestiondeshopitauxbackend.ENTITIES.*;
import gestiondeshopitauxbackend.MAPPERS.ServiceMapper;
import gestiondeshopitauxbackend.REPOSITORIES.*;
import gestiondeshopitauxbackend.SERVICES.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ServiceServiceImpl implements ServiceService {

    private static final Logger logger = LoggerFactory.getLogger(ServiceServiceImpl.class);
    private final ServiceRepository serviceRepository;
    private final HopitalRepository hopitalRepository;
    private final ServiceMapper mapper;

    @Override
    public ServiceDTO getService(Long id) {
        ServiceEntity service = serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));
        return mapper.fromService(service);
    }

    @Override
    public List<ServiceDTO> listServices() {
        try {
            logger.info("Récupération de tous les services");
            List<ServiceEntity> services = serviceRepository.findAll();
            logger.info("Nombre de services trouvés : {}", services.size());
            
            return services.stream()
                    .map(service -> {
                        try {
                            return mapper.fromService(service);
                        } catch (Exception e) {
                            logger.error("Erreur lors de la conversion du service {} : {}", service.getIdService(), e.getMessage());
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .toList();
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération des services", e);
            throw e;
        }
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
        try {
            ServiceEntity service = serviceRepository.findById(dto.getIdService())
                    .orElseThrow(() -> new RuntimeException("Service non trouvé"));

            Hopital hopital = hopitalRepository.findById(dto.getHopital().getIdHopital())
                    .orElseThrow(() -> new RuntimeException("Hôpital non trouvé"));

            service.setNom(dto.getNom());
            service.setDescription(dto.getDescription());
            service.setHopital(hopital);

            ServiceEntity updated = serviceRepository.save(service);
            return mapper.fromService(updated);
        } catch (ObjectOptimisticLockingFailureException e) {
            throw new RuntimeException("La ressource a été modifiée par un autre utilisateur. Veuillez réessayer.", e);
        }
    }

    @Override
    public List<ServiceDTO> getServicesByHopital(Long hopitalId) {
        return serviceRepository.findByHopital_IdHopital(hopitalId)
                .stream()
                .map(mapper::fromService)
                .toList();
    }

    @Override
    public List<SousServiceDTO> getSousServicesByService(Long serviceId) {
        ServiceEntity service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service not found"));
        
        List<SousService> sousServices = service.getSousServices();
        if (sousServices == null) {
            return new ArrayList<>();
        }
        
        return sousServices.stream()
                .map(sous -> {
                    SousServiceDTO dto = new SousServiceDTO(
                        sous.getIdSousService(), 
                        sous.getNom(), 
                        sous.getDescription());
                    dto.setServiceId(serviceId);
                    return dto;
                })
                .toList();
    }
}
