package labrini.ouiam.gestiondeshopitauxbackendv1.SERVICES.IMPL;

import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.MedicamentDTO;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.Medicament;
import labrini.ouiam.gestiondeshopitauxbackendv1.EXCEPTIONS.ResourceNotFoundException;
import labrini.ouiam.gestiondeshopitauxbackendv1.MAPPERS.MedicamentMapper;
import labrini.ouiam.gestiondeshopitauxbackendv1.REPOSITORIES.MedicamentRepository;
import labrini.ouiam.gestiondeshopitauxbackendv1.SERVICES.MedicamentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MedicamentServiceImpl implements MedicamentService {

    private final MedicamentRepository medicamentRepository;
    private final MedicamentMapper medicamentMapper;

    public MedicamentServiceImpl(MedicamentRepository medicamentRepository,
                                 MedicamentMapper medicamentMapper) {
        this.medicamentRepository = medicamentRepository;
        this.medicamentMapper = medicamentMapper;
    }

    @Override
    public MedicamentDTO createMedicament(MedicamentDTO medicamentDTO) {
        Medicament medicament = medicamentMapper.toEntity(medicamentDTO);
        Medicament savedMedicament = medicamentRepository.save(medicament);
        return medicamentMapper.toDto(savedMedicament);
    }

    @Override
    public MedicamentDTO getMedicamentById(Long id) {
        Medicament medicament = medicamentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medicament", "id", id));
        return medicamentMapper.toDto(medicament);
    }

    @Override
    public List<MedicamentDTO> getAllMedicaments() {
        return medicamentRepository.findAll().stream()
                .map(medicamentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MedicamentDTO> searchMedicaments(String keyword) {
        return medicamentRepository.findByNomContainingIgnoreCase(keyword).stream()
                .map(medicamentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MedicamentDTO updateMedicament(Long id, MedicamentDTO medicamentDTO) {
        Medicament existingMedicament = medicamentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medicament", "id", id));

        medicamentMapper.toEntity(medicamentDTO);
        Medicament updatedMedicament = medicamentRepository.save(existingMedicament);
        return medicamentMapper.toDto(updatedMedicament);
    }

    @Override
    public void deleteMedicament(Long id) {
        Medicament medicament = medicamentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medicament", "id", id));
        medicamentRepository.delete(medicament);
    }
}