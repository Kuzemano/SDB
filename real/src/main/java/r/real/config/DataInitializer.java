package r.real.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import r.real.common.domain.valueObjects.Currency;
import r.real.drivers.service.DriverService;
import r.real.users.domain.valueObjects.Role;
import r.real.users.service.UserService;

import java.math.BigDecimal;

@Component
public class DataInitializer {

    private final DriverService driverService;
    private final UserService userService;
    @Value("${app.user.username}") private String userUsername;
    @Value("${app.user.password}") private String userPassword;
    @Value("${app.admin.username}") private String adminUsername;
    @Value("${app.admin.password}") private String adminPassword;

    public DataInitializer(DriverService driverService, UserService userService) {
        this.driverService = driverService; this.userService= userService;
    }

    @PostConstruct
    public void initData() {
        userService.create(userUsername, userPassword, Role.ROLE_USER);
        userService.create(adminUsername, adminPassword, Role.ROLE_ADMIN);
        driverService.create("Max Verstappen", "Red Bull Racing", "Dutch", 1, new BigDecimal("55000000"), Currency.EUR, 575);
        driverService.create("Lewis Hamilton", "Ferrari", "British", 44, new BigDecimal("50000000"), Currency.EUR, 285);
        driverService.create("Charles Leclerc", "Ferrari", "Monegasque", 16, new BigDecimal("30000000"), Currency.EUR, 307);
        driverService.create("Lando Norris", "McLaren", "British", 4, new BigDecimal("20000000"), Currency.EUR, 374);
        driverService.create("Carlos Sainz", "Williams", "Spanish", 55, new BigDecimal("12000000"), Currency.EUR, 290);
    }
}