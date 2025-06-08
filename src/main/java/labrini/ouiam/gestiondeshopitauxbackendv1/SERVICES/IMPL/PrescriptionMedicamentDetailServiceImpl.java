package labrini.ouiam.gestiondeshopitauxbackendv1.SERVICES.IMPL;

import labrini.ouiam.gestiondeshopitauxbackendv1.DTOS.PrescriptionMedicamentDetailDTO;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.Medicament;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.PrescriptionMedical;
import labrini.ouiam.gestiondeshopitauxbackendv1.ENTITIES.PrescriptionMedicamentDetail;
import labrini.ouiam.gestiondeshopitauxbackendv1.EXCEPTIONS.ResourceNotFoundException;
import labrini.ouiam.gestiondeshopitauxbackendv1.REPOSITORIES.MedicamentRepository;
import labrini.ouiam.gestiondeshopitauxbackendv1.REPOSITORIES.PrescriptionMedicalRepository;
import labrini.ouiam.gestiondeshopitauxbackendv1.REPOSITORIES.PrescriptionMedicamentDetailRepository;
import labrini.ouiam.gestiondeshopitauxbackendv1.SERVICES.PrescriptionMedicamentDetailService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
/*
@Service
@Transactional
public class PrescriptionMedicamentDetailServiceImpl implements PrescriptionMedicamentDetailService {

    private final PrescriptionMedicamentDetailRepository detailRepository;
    private final PrescriptionMedicalRepository prescriptionRepository;
    private final MedicamentRepository medicamentRepository;
    private final PrescriptionMedicamentDetailMapper detailMapper;

    public PrescriptionMedicamentDetailServiceImpl(PrescriptionMedicamentDetailRepository detailRepository,
                                                   PrescriptionMedicalRepository prescriptionRepository,
                                                   MedicamentRepository medicamentRepository,
                                                   PrescriptionMedicamentDetailMapper detailMapper) {
        this.detailRepository = detailRepository;
        this.prescriptionRepository = prescriptionRepository;
        this.medicamentRepository = medicamentRepository;
        this.detailMapper = detailMapper;
    }

    @Override
    public PrescriptionMedicamentDetailDTO createDetail(PrescriptionMedicamentDetailDTO detailDTO) {
        PrescriptionMedical prescription = prescriptionRepository.findById(detailDTO.getPrescriptionMedicalId())
                .orElseThrow(() -> new ResourceNotFoundException("PrescriptionMedical", "id", detailDTO.getPrescriptionMedicalId()));

        Medicament medicament = medicamentRepository.findById(detailDTO.getMedicamentId())
                .orElseThrow(() -> new ResourceNotFoundException("Medicament", "id", detailDTO.getMedicamentId()));

        PrescriptionMedicamentDetail detail = detailMapper.toEntity(detailDTO);
        detail.setPrescription(prescription);
        detail.setMedicament(medicament);

        PrescriptionMedicamentDetail savedDetail = detailRepository.save(detail);
        return detailMapper.toDto(savedDetail);
    }

    @Override
    public PrescriptionMedicamentDetailDTO getDetailById(Long id) {
        PrescriptionMedicamentDetail detail = detailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PrescriptionMedicamentDetail", "id", id));
        return detailMapper.toDto(detail);
    }

    @Override
    public List<PrescriptionMedicamentDetailDTO> getDetailsByPrescription(Long prescriptionId) {
        return detailRepository.findByPrescription_IdPrescriptionMedical(prescriptionId).stream()
                .map(detailMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PrescriptionMedicamentDetailDTO updateDetail(Long id, PrescriptionMedicamentDetailDTO detailDTO) {
        PrescriptionMedicamentDetail existingDetail = detailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PrescriptionMedicamentDetail", "id", id));

        detailMapper.toEntity(detailDTO);
        PrescriptionMedicamentDetail updatedDetail = detailRepository.save(existingDetail);
        return detailMapper.toDto(updatedDetail);
    }

    @Override
    public void deleteDetail(Long id) {
        PrescriptionMedicamentDetail detail = detailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PrescriptionMedicamentDetail", "id", id));
        detailRepository.delete(detail);
    }
}*/

@Service
@Transactional
public class PrescriptionMedicamentDetailServiceImpl implements PrescriptionMedicamentDetailService {

    private final PrescriptionMedicamentDetailRepository detailRepository;
    private final PrescriptionMedicalRepository prescriptionRepository;
    private final MedicamentRepository medicamentRepository;

    public PrescriptionMedicamentDetailServiceImpl(PrescriptionMedicamentDetailRepository detailRepository,
                                                   PrescriptionMedicalRepository prescriptionRepository,
                                                   MedicamentRepository medicamentRepository) {
        this.detailRepository = detailRepository;
        this.prescriptionRepository = prescriptionRepository;
        this.medicamentRepository = medicamentRepository;
    }

    @Override
    public PrescriptionMedicamentDetailDTO createDetail(PrescriptionMedicamentDetailDTO detailDTO) {
        PrescriptionMedical prescription = prescriptionRepository.findById(detailDTO.getPrescriptionId())
                .orElseThrow(() -> new ResourceNotFoundException("PrescriptionMedical", "id", detailDTO.getPrescriptionId()));

        Medicament medicament = medicamentRepository.findById(detailDTO.getMedicamentId())
                .orElseThrow(() -> new ResourceNotFoundException("Medicament", "id", detailDTO.getMedicamentId()));

        PrescriptionMedicamentDetail detail = new PrescriptionMedicamentDetail();
        BeanUtils.copyProperties(detailDTO, detail);
        detail.setPrescription(prescription);
        detail.setMedicament(medicament);

        PrescriptionMedicamentDetail savedDetail = detailRepository.save(detail);
        return toDto(savedDetail);
    }

    @Override
    public PrescriptionMedicamentDetailDTO getDetailById(Long id) {
        PrescriptionMedicamentDetail detail = detailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PrescriptionMedicamentDetail", "id", id));
        return toDto(detail);
    }

    @Override
    public List<PrescriptionMedicamentDetailDTO> getDetailsByPrescription(Long prescriptionId) {
        return detailRepository.findByPrescription_IdPrescriptionMedical(prescriptionId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PrescriptionMedicamentDetailDTO updateDetail(Long id, PrescriptionMedicamentDetailDTO detailDTO) {
        PrescriptionMedicamentDetail existingDetail = detailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PrescriptionMedicamentDetail", "id", id));

        BeanUtils.copyProperties(detailDTO, existingDetail, "id", "prescription", "medicament");

        if(detailDTO.getPrescriptionId() != null) {
            PrescriptionMedical prescription = prescriptionRepository.findById(detailDTO.getPrescriptionId())
                    .orElseThrow(() -> new ResourceNotFoundException("PrescriptionMedical", "id", detailDTO.getPrescriptionId()));
            existingDetail.setPrescription(prescription);
        }

        if(detailDTO.getMedicamentId() != null) {
            Medicament medicament = medicamentRepository.findById(detailDTO.getMedicamentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Medicament", "id", detailDTO.getMedicamentId()));
            existingDetail.setMedicament(medicament);
        }

        PrescriptionMedicamentDetail updatedDetail = detailRepository.save(existingDetail);
        return toDto(updatedDetail);
    }

    @Override
    public void deleteDetail(Long id) {
        PrescriptionMedicamentDetail detail = detailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PrescriptionMedicamentDetail", "id", id));
        detailRepository.delete(detail);
    }

    private PrescriptionMedicamentDetailDTO toDto(PrescriptionMedicamentDetail detail) {
        PrescriptionMedicamentDetailDTO dto = new PrescriptionMedicamentDetailDTO();
        BeanUtils.copyProperties(detail, dto);

        if(detail.getPrescription() != null) {
            dto.setPrescriptionId(detail.getPrescription().getIdPrescriptionMedical());
            dto.setPrescriptionDescription(detail.getPrescription().getDescription());
        }

        if(detail.getMedicament() != null) {
            dto.setMedicamentId(detail.getMedicament().getIdMedicament());
            dto.setMedicamentNom(detail.getMedicament().getNom());
            dto.setMedicamentDescription(detail.getMedicament().getDescription());
        }

        return dto;
    }
}