package gestiondeshopitauxbackend.SERVICES;

import gestiondeshopitauxbackend.DTOS.ServiceDTO;
import java.util.List;

public interface ServiceService {
    ServiceDTO getService(Long id);
    List<ServiceDTO> listServices();
    ServiceDTO saveService(ServiceDTO dto);
    void deleteService(Long id);
}

