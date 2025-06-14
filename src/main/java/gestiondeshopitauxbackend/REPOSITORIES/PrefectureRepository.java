package gestiondeshopitauxbackend.REPOSITORIES;

import gestiondeshopitauxbackend.ENTITIES.Prefecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PrefectureRepository extends JpaRepository<Prefecture, Long> {
    Optional<Prefecture> findByNom(String nom);
    List<Prefecture> findByRegion_IdRegion(Long regionId);
}
