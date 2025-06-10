package gestiondeshopitauxbackend.SERVICES;

import gestiondeshopitauxbackend.DTOS.PrefectureDTO;
import java.util.List;

public interface PrefectureService {
    PrefectureDTO createPrefecture(PrefectureDTO prefectureDTO);
    PrefectureDTO getPrefectureById(Long id);
    List<PrefectureDTO> getAllPrefectures();
    List<PrefectureDTO> getPrefecturesByRegion(Long regionId);
    PrefectureDTO updatePrefecture(Long id, PrefectureDTO prefectureDTO);
    void deletePrefecture(Long id);
}