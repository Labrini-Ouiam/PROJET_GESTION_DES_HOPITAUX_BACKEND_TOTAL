package labrini.ouiam.gestiondeshopitauxbackendv1.DTOS;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RendezVousDTO {
    private Long id;
    private Date dateRendezVous;
    private String motif;
    private String statut;
    private PatientDTO patient;
}
