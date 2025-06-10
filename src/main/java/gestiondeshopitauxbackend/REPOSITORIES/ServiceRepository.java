package gestiondeshopitauxbackend.REPOSITORIES;

import gestiondeshopitauxbackend.ENTITIES.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {
    List<ServiceEntity> findByHopital_IdHopital(Long hopitalId);
}
