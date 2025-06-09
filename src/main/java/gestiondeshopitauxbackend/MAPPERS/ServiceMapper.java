package gestiondeshopitauxbackend.MAPPERS;

import gestiondeshopitauxbackend.DTOS.ServiceDTO;
import gestiondeshopitauxbackend.DTOS.SousServiceDTO;
import gestiondeshopitauxbackend.ENTITIES.Hopital;
import gestiondeshopitauxbackend.ENTITIES.ServiceEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceMapper {

    public ServiceDTO fromService(ServiceEntity service) {
        ServiceDTO dto = new ServiceDTO();
        dto.setIdService(service.getIdService());
        dto.setNom(service.getNom());
        dto.setDescription(service.getDescription());
        dto.setHopital(service.getHopital());

        List<SousServiceDTO> sousDtos = service.getSousServices()
                .stream()
                .map(sous -> new SousServiceDTO(sous.getIdSousService(), sous.getNom(), sous.getDescription(), null))
                .toList();

        dto.setSousServicesDTO(sousDtos);

        return dto;
    }

    public ServiceEntity toService(ServiceDTO dto, Hopital hopital) {
        ServiceEntity service = new ServiceEntity();
        service.setIdService(dto.getIdService());
        service.setNom(dto.getNom());
        service.setDescription(dto.getDescription());
        service.setHopital(hopital);
        return service;
    }
}
