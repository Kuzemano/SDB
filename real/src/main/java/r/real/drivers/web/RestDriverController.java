package r.real.drivers.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import r.real.drivers.domain.Driver;
import r.real.drivers.service.DriverService;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
public class RestDriverController {

    private final DriverService driverService;

    public RestDriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping
    public List<Driver> listAll() {
        return driverService.listAll();
    }
}




/*
package r.real.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import r.real.service.DriverService;

@RestController
@RequestMapping("/api/drivers")
public class RestDriverController {

    private final DriverService driverService;
    private final DriverServiceFaultyImpl faultyService;

    public RestDriverController(DriverService driverService,
                                DriverServiceFaultyImpl faultyService) {
        this.driverService = driverService;
        this.faultyService = faultyService;
    }

    @GetMapping
    public List<Driver> listAll() {
        return driverService.listAll();
    }

    @GetMapping("/{id}")
    public Driver findById(@PathVariable Long id) {
        return driverService.findById(id);
    }

    @GetMapping("/by-name/{name}")
    public List<Driver> findByName(@PathVariable String name) {
        return driverService.findByName(name);
    }

    @GetMapping("/by-name-sqli/{name}")
    public List<Driver> findByNameSqli(@PathVariable String name) {
        return faultyService.findByNameVulnerable(name);
    }

    @PostMapping
    public Driver create(@RequestBody Driver driver) {
        return driverService.create(
                driver.getName(), driver.getTeamName(), driver.getNationality(),
                driver.getRacingNumber(), driver.getSalary(), driver.getSalaryCurrency(),
                driver.getChampionshipPoints());
    }

    @PutMapping("/{id}")
    public Driver update(@PathVariable Long id, @RequestBody Driver driver) {
        return driverService.update(id,
                driver.getName(), driver.getTeamName(), driver.getNationality(),
                driver.getRacingNumber(), driver.getSalary(), driver.getSalaryCurrency(),
                driver.getChampionshipPoints());
    }

    @DeleteMapping("/{id}")
    public Driver delete(@PathVariable Long id) {
        return driverService.delete(id);
    }

    @PostMapping("/{id}/points")
    public Driver addPoints(@PathVariable Long id, @RequestParam Integer points) {
        return driverService.addPoints(id, points);
    }
}*/