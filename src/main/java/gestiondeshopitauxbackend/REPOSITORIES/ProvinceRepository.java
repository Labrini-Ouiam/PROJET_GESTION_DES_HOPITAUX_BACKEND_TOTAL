package gestiondeshopitauxbackend.REPOSITORIES;

import gestiondeshopitauxbackend.ENTITIES.Province;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProvinceRepository extends JpaRepository<Province, Long> {
    Optional<Province> findByNom(String nom);
    List<Province> findByRegion_IdRegion(Long regionId);
}
