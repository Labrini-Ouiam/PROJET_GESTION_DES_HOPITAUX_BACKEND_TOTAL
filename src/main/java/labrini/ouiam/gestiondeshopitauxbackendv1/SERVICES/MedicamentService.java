package labrini.ouiam.gestiondeshopitauxbackendv1.SERVICES;

import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.MedicamentDTO;
import java.util.List;

public interface MedicamentService {
    MedicamentDTO createMedicament(MedicamentDTO medicamentDTO);
    MedicamentDTO getMedicamentById(Long id);
    List<MedicamentDTO> getAllMedicaments();
    List<MedicamentDTO> searchMedicaments(String keyword);
    MedicamentDTO updateMedicament(Long id, MedicamentDTO medicamentDTO);
    void deleteMedicament(Long id);
}