package gestiondeshopitauxbackend.SERVICES;

import gestiondeshopitauxbackend.DTOS.HopitalDTO;

import java.util.List;

public interface HopitalService {
    HopitalDTO createHopital(HopitalDTO hopitalDTO);
    List<HopitalDTO> getAllHopitaux();
    HopitalDTO getHopitalById(Long id);
    HopitalDTO updateHopital(Long id, HopitalDTO hopitalDTO);
    void deleteHopital(Long id);
}
