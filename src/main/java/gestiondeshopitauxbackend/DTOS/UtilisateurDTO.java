package gestiondeshopitauxbackend.DTOS;
import gestiondeshopitauxbackend.ENUMS.SpecialiteInfermier;
import gestiondeshopitauxbackend.ENUMS.SpecialiteMedecin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurDTO {
    private Long idUtilisateur;
    private String nom;
    private String prenom;
    private String cin;
    private LocalDate dateNaissance;
    private String email;
    private String telephone;
    private String adresse;
    private String assuranceSocial;
    private String role;
    private SpecialiteMedecin specialiteMedecin;
    private SpecialiteInfermier specialiteInfermier;
}
