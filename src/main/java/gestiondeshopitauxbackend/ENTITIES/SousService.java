package gestiondeshopitauxbackend.ENTITIES;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class SousService {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSousService;
    private String nom;
    private String description;

    @ManyToOne
    private Service service;
}
