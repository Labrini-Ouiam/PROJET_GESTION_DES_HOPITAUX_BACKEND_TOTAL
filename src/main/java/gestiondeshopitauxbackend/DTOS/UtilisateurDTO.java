package gestiondeshopitauxbackend.DTOS;
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
    private String specialiteMedecin;
    private String specialiteInfermier;
    private boolean s = false;
    private int codeUser;
    private LocalDateTime dateLastAction;
}
