package labrini.ouiam.gestiondeshopitauxbackendv1.WEB;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Hospital Management API is running";
    }

    @GetMapping("/api/health")
    public String health() {
        return "UP";
    }
}