package gestiondeshopitauxbackend.REPOSITORIES;

import gestiondeshopitauxbackend.ENTITIES.TypeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeUserRepository extends JpaRepository<TypeUser, Long> {
    TypeUser findByRole(String role);
}