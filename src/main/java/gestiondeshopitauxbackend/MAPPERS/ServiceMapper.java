package gestiondeshopitauxbackend.MAPPERS;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gestiondeshopitauxbackend.DTOS.HopitalDTO;
import gestiondeshopitauxbackend.DTOS.ServiceDTO;
import gestiondeshopitauxbackend.DTOS.SousServiceDTO;
import gestiondeshopitauxbackend.ENTITIES.Hopital;
import gestiondeshopitauxbackend.ENTITIES.ServiceEntity;
import gestiondeshopitauxbackend.ENTITIES.SousService;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceMapper {

    private static final Logger logger = LoggerFactory.getLogger(ServiceMapper.class);

    public ServiceDTO fromService(ServiceEntity service) {
        if (service == null) {
            return null;
        }
        
        try {
            ServiceDTO dto = new ServiceDTO();
            dto.setIdService(service.getIdService());
            dto.setNom(service.getNom());
            dto.setDescription(service.getDescription());
            
            // Créer un DTO simplifié pour l'hôpital pour éviter les références circulaires
            if (service.getHopital() != null) {
                HopitalDTO hopitalDTO = new HopitalDTO();
                hopitalDTO.setIdHopital(service.getHopital().getIdHopital());
                hopitalDTO.setNom(service.getHopital().getNom());
                dto.setHopital(hopitalDTO);
            }
            
            // Gérer le cas où sousServices est null
            if (service.getSousServices() != null) {
                List<SousServiceDTO> sousServiceDTOs = service.getSousServices().stream()
                        .map(this::toSousServiceDTO)
                        .toList();
                dto.setSousServicesDTO(sousServiceDTOs);
            } else {
                dto.setSousServicesDTO(new ArrayList<>());
            }
            
            return dto;
        } catch (Exception e) {
            logger.error("Erreur lors de la conversion du service en DTO", e);
            throw e;
        }
    }
    
    private SousServiceDTO toSousServiceDTO(SousService sousService) {
        if (sousService == null) {
            return null;
        }
        
        SousServiceDTO dto = new SousServiceDTO();
        dto.setIdSousService(sousService.getIdSousService());
        dto.setNom(sousService.getNom());
        dto.setDescription(sousService.getDescription());
        if (sousService.getService() != null) {
            dto.setServiceId(sousService.getService().getIdService());
        }
        return dto;
    }
    
    public ServiceEntity toService(ServiceDTO dto, Hopital hopital) {
        if (dto == null) {
            return null;
        }
        
        ServiceEntity entity = new ServiceEntity();
        if (dto.getIdService() != null) {
            entity.setIdService(dto.getIdService());
        }
        entity.setNom(dto.getNom());
        entity.setDescription(dto.getDescription());
        entity.setHopital(hopital);
        
        // Gérer les sous-services si présents
        if (dto.getSousServicesDTO() != null && !dto.getSousServicesDTO().isEmpty()) {
            List<SousService> sousServices = dto.getSousServicesDTO().stream()
                    .map(sousDto -> {
                        SousService sousService = new SousService();
                        sousService.setNom(sousDto.getNom());
                        sousService.setDescription(sousDto.getDescription());
                        sousService.setService(entity); // Référence circulaire gérée
                        return sousService;
                    })
                    .toList();
            entity.setSousServices(sousServices);
        }
        
        return entity;
    }
}