package labrini.ouiam.gestiondeshopitauxbackendv1.SERVICES;

import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.ResultatExamenDTO;
import java.util.List;

public interface ResultatExamenService {
    ResultatExamenDTO createResultat(ResultatExamenDTO resultatDTO);
    ResultatExamenDTO getResultatById(Long id);
    List<ResultatExamenDTO> getResultatsByDossier(Long dossierId);
    ResultatExamenDTO updateResultat(Long id, ResultatExamenDTO resultatDTO);
    void deleteResultat(Long id);
}