package gestiondeshopitauxbackend.DTOS;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDTO {
    private Long idService;
    private String nom;
    private String description;
    
    @JsonIgnoreProperties({"services", "province", "prefecture", "region"})
    private HopitalDTO hopital;
    
    private List<SousServiceDTO> sousServicesDTO = new ArrayList<>();
}
