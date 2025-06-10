package gestiondeshopitauxbackend.ENTITIES;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sous_service")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SousService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSousService;
    private String nom;
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServiceEntity service;
}
