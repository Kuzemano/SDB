package r.real.config;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import r.real.service.DriverService;

@Component
public class DataInitializer {

    private final DriverService driverService;

    public DataInitializer(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostConstruct
    public void initData() {
        driverService.create("Max Verstappen",  "Red Bull Racing", "Dutch",       1,  55000000.0, "EUR", 575);
        driverService.create("Lewis Hamilton",  "Ferrari",         "British",     44, 50000000.0, "EUR", 285);
        driverService.create("Charles Leclerc", "Ferrari",         "Monegasque",  16, 30000000.0, "EUR", 307);
        driverService.create("Lando Norris",    "McLaren",         "British",     4,  20000000.0, "EUR", 374);
        driverService.create("Carlos Sainz",    "Williams",        "Spanish",     55, 12000000.0, "EUR", 290);
        driverService.create("George Russell",  "Mercedes",        "British",     63, 18000000.0, "EUR", 245);
        driverService.create("Fernando Alonso", "Aston Martin",    "Spanish",     14, 20000000.0, "EUR", 206);
        driverService.create("Oscar Piastri",   "McLaren",         "Australian",  81, 10000000.0, "EUR", 292);
        driverService.create("Sergio Perez",    "Red Bull Racing", "Mexican",     11, 15000000.0, "EUR", 152);
        driverService.create("Lance Stroll",    "Aston Martin",    "Canadian",    18,  8000000.0, "EUR", 74);
    }
}