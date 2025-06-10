package gestiondeshopitauxbackend.DTOS;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HopitalDTO {
    private Long idHopital;
    private String nom;
    private String adresse;
    private String province;
    private String prefecture;
    private String region;
    
    // Constructeur simplifié pour éviter les références circulaires
    public HopitalDTO(Long idHopital, String nom) {
        this.idHopital = idHopital;
        this.nom = nom;
    }
}