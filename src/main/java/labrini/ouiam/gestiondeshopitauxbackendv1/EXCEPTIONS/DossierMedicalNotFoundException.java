package labrini.ouiam.gestiondeshopitauxbackendv1.EXCEPTIONS;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DossierMedicalNotFoundException extends ResourceNotFoundException {
    public DossierMedicalNotFoundException(Long dossierId) {
        super("Dossier Medical", "id", dossierId);
    }
}