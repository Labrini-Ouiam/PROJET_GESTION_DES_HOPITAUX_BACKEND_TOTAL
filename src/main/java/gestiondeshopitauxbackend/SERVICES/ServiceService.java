package gestiondeshopitauxbackend.SERVICES;

import gestiondeshopitauxbackend.DTOS.ServiceDTO;
import gestiondeshopitauxbackend.DTOS.SousServiceDTO;
import java.util.List;

public interface ServiceService {
    ServiceDTO getService(Long id);
    List<ServiceDTO> listServices();
    ServiceDTO saveService(ServiceDTO dto);
    void deleteService(Long id);
    ServiceDTO updateService(ServiceDTO dto);
    
    // Nouvelles méthodes
    List<ServiceDTO> getServicesByHopital(Long hopitalId);
    List<SousServiceDTO> getSousServicesByService(Long serviceId);
}

