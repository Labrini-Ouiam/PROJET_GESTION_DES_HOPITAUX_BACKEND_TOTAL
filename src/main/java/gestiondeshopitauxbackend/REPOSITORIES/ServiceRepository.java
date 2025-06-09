package gestiondeshopitauxbackend.REPOSITORIES;

import gestiondeshopitauxbackend.ENTITIES.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {
}
