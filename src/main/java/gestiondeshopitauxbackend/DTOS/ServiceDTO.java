package gestiondeshopitauxbackend.DTOS;

import gestiondeshopitauxbackend.ENTITIES.Hopital;
import gestiondeshopitauxbackend.ENTITIES.SousService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
public class ServiceDTO {
    private Long idService;
    private String nom;
    private String description;
    private Hopital hopital;
    private List<SousServiceDTO> sousServicesDTO;
}