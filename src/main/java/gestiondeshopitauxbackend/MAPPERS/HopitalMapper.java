package gestiondeshopitauxbackend.MAPPERS;


import gestiondeshopitauxbackend.DTOS.HopitalDTO;
import gestiondeshopitauxbackend.DTOS.MedicamentDTO;
import gestiondeshopitauxbackend.ENTITIES.*;
import org.springframework.beans.BeanUtils;

public class HopitalMapper {

    public HopitalDTO toDto(Hopital hopital) {
        HopitalDTO hopitalDTO = new HopitalDTO();
        BeanUtils.copyProperties(hopital, hopitalDTO);
        hopitalDTO.setPrefecture(hopital.getPrefecture()!=null? hopital.getPrefecture().getNom():null);
        hopitalDTO.setProvince(hopital.getProvince()!=null? hopital.getProvince().getNom():null);
        return hopitalDTO;
    }
    public Hopital toDto(HopitalDTO hopitalDTO) {
        Hopital hopital = new Hopital();
        BeanUtils.copyProperties(hopitalDTO, hopital);
        if (hopitalDTO.getProvince()!=null){
           Province province= new Province();
           province.setNom(hopitalDTO.getProvince());
           hopital.setProvince(province);
        }
        if (hopitalDTO.getPrefecture()!=null){
            Prefecture prefecture= new Prefecture();
            prefecture.setNom(hopitalDTO.getProvince());
            hopital.setPrefecture(prefecture);
        }
        return hopital;
    }
}