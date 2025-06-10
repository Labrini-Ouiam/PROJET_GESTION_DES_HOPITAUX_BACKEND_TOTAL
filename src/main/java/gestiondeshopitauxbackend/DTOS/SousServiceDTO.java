package gestiondeshopitauxbackend.DTOS;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SousServiceDTO {
    private Long idSousService;
    private String nom;
    private String description;
    
    // Ne pas inclure l'objet service complet, juste l'ID
    private Long serviceId;
    
    public SousServiceDTO(Long idSousService, String nom, String description) {
        this.idSousService = idSousService;
        this.nom = nom;
        this.description = description;
    }
}
