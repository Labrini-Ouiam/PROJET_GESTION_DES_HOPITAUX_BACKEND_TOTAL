package gestiondeshopitauxbackend.DTOS;


import jakarta.persistence.*;

public class HopitalDTO {
    private Long idHopital;
    private String nom;
    private String adresse;
    private String province;
    private String prefecture;
}