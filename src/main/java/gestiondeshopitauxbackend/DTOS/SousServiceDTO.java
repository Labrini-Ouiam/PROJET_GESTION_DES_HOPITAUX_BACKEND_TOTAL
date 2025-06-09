package gestiondeshopitauxbackend.DTOS;

import gestiondeshopitauxbackend.ENTITIES.ServiceEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class SousServiceDTO {
    private Long idSousService;
    private String nom;
    private String description;
    private ServiceEntity service;

    public SousServiceDTO(Long idSousService, String nom, String description) {
        this.idSousService=idSousService;
        this.nom=nom;
        this.description=description;
    }
}
