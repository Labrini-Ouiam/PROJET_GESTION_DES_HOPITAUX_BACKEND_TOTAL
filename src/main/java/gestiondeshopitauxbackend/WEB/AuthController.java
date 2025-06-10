package gestiondeshopitauxbackend.WEB;

import gestiondeshopitauxbackend.DTOS.JwtResponse;
import gestiondeshopitauxbackend.ENTITIES.Utilisateur;
import gestiondeshopitauxbackend.REPOSITORIES.UtilisateurRepository;
import gestiondeshopitauxbackend.ENTITIES.TypeUser;
import gestiondeshopitauxbackend.SERVICES.UtilisateurService;
import gestiondeshopitauxbackend.REPOSITORIES.TypeUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private TypeUserRepository typeUserRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        logger.info("[DEBUG-AUTH] Tentative de connexion pour l'email : {}", loginRequest.getEmail());

        Utilisateur utilisateur = utilisateurRepository.findByEmail(loginRequest.getEmail());
        if (utilisateur == null) {
            logger.warn("[DEBUG-AUTH] Email non trouvé : {}", loginRequest.getEmail());
            Map<String, String> error = new HashMap<>();
            error.put("error", "Email ou mot de passe incorrect");
            return ResponseEntity.badRequest().body(error);
        }

        // Log du hash stocké pour debug
        logger.info("[DEBUG-AUTH] Hash stocké en base : {}", utilisateur.getPassword());

        // Création d'un nouveau hash pour comparaison
        String newHash = passwordEncoder.encode(loginRequest.getPassword());
        logger.info("[DEBUG-AUTH] Nouveau hash généré : {}", newHash);

        boolean matches = passwordEncoder.matches(loginRequest.getPassword(), utilisateur.getPassword());
        logger.info("[DEBUG-AUTH] Résultat de la comparaison : {}", matches);

        if (!matches) {
            logger.warn("[DEBUG-AUTH] Mot de passe incorrect pour l'email : {}", loginRequest.getEmail());
            Map<String, String> error = new HashMap<>();
            error.put("error", "Email ou mot de passe incorrect");
            return ResponseEntity.badRequest().body(error);
        }

        String role = utilisateur.getTypeUser().getRole();
        String token = jwtUtil.generateToken(utilisateur.getEmail(), role);
        logger.info("[DEBUG-AUTH] Connexion réussie pour l'utilisateur : {} avec le rôle : {}", utilisateur.getEmail(), role);

        return ResponseEntity.ok(new JwtResponse(token, "Bearer", utilisateur.getIdUtilisateur(), utilisateur.getEmail(), role));
    }

    @PostMapping("/register/patient")
    public ResponseEntity<?> registerPatient(@RequestBody RegisterPatientRequest request) {
        if (utilisateurRepository.findByEmail(request.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Email déjà utilisé");
        }
        Utilisateur patient = new Utilisateur();
        patient.setNom(request.getNom());
        patient.setPrenom(request.getPrenom());
        patient.setCin(request.getCin());
        patient.setEmail(request.getEmail());
        patient.setPassword(passwordEncoder.encode(request.getPassword()));
        patient.setTelephone(request.getTelephone());
        patient.setAdresse(request.getAdresse());
        patient.setAssuranceSocial(request.getAssuranceSocial());
        // Conversion date string -> LocalDate à ajouter si besoin
        // patient.setDateNaissance(LocalDate.parse(request.getDateNaissance()));
        TypeUser typePatient = typeUserRepository.findByRole("PATIENT");
        patient.setTypeUser(typePatient);
        utilisateurRepository.save(patient);
        return ResponseEntity.ok("Patient inscrit avec succès");
    }

    // DTO pour la requête de login
    public static class LoginRequest {
        private String email;
        private String password;
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    // DTO pour la requête d'inscription patient
    public static class RegisterPatientRequest {
        private String nom;
        private String prenom;
        private String cin;
        private String email;
        private String password;
        private String telephone;
        private String adresse;
        private String assuranceSocial;
        // private String dateNaissance;
        public String getNom() { return nom; }
        public void setNom(String nom) { this.nom = nom; }
        public String getPrenom() { return prenom; }
        public void setPrenom(String prenom) { this.prenom = prenom; }
        public String getCin() { return cin; }
        public void setCin(String cin) { this.cin = cin; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
        public String getTelephone() { return telephone; }
        public void setTelephone(String telephone) { this.telephone = telephone; }
        public String getAdresse() { return adresse; }
        public void setAdresse(String adresse) { this.adresse = adresse; }
        public String getAssuranceSocial() { return assuranceSocial; }
        public void setAssuranceSocial(String assuranceSocial) { this.assuranceSocial = assuranceSocial; }
        // public String getDateNaissance() { return dateNaissance; }
        // public void setDateNaissance(String dateNaissance) { this.dateNaissance = dateNaissance; }
    }
}
