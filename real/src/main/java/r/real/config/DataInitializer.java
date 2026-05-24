package r.real.config;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import r.real.common.domain.valueObjects.Currency;
import r.real.drivers.service.DriverService;

import java.math.BigDecimal;

@Component
public class DataInitializer {

    private final DriverService driverService;

    public DataInitializer(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostConstruct
    public void initData() {
        driverService.create("Max Verstappen", "Red Bull Racing", "Dutch", 1, new BigDecimal("55000000"), Currency.EUR, 575);
        driverService.create("Lewis Hamilton", "Ferrari", "British", 44, new BigDecimal("50000000"), Currency.EUR, 285);
        driverService.create("Charles Leclerc", "Ferrari", "Monegasque", 16, new BigDecimal("30000000"), Currency.EUR, 307);
        driverService.create("Lando Norris", "McLaren", "British", 4, new BigDecimal("20000000"), Currency.EUR, 374);
        driverService.create("Carlos Sainz", "Williams", "Spanish", 55, new BigDecimal("12000000"), Currency.EUR, 290);
    }
}