package r.real.service.impl;

import org.springframework.stereotype.Service;
import r.real.model.*;
import r.real.model.exceptions.InvalidDriverIdException;
import r.real.model.form.DriverForm;
import r.real.model.valueObjects.Currency;
import r.real.model.valueObjects.*;
import r.real.repository.DriverRepository;
import r.real.service.DriverService;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;

    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public List<Driver> listAll() {
        return driverRepository.findAll();
    }

    @Override
    public Driver findById(DriverId id) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new InvalidDriverIdException(id));
    }

    @Override
    public List<Driver> findByName(String name) {
        return driverRepository.findByNameValue(name);
    }

    @Override
    public List<Driver> findByTeam(String teamName) {
        return driverRepository.findByTeamNameValue(teamName);
    }

    @Override
    public Driver create(String name, String teamName, String nationality,
                         Integer racingNumber, BigDecimal salary,
                         Currency salaryCurrency, Integer championshipPoints) {
        Driver driver = new Driver(
                new DriverName(name),
                new TeamName(teamName),
                new Nationality(nationality),
                new RacingNumber(racingNumber),
                new Money(salaryCurrency, salary),
                new ChampionshipPoints(championshipPoints)
        );
        return driverRepository.save(driver);
    }

    @Override
    public Driver create(DriverForm form) {
        return driverRepository.save(
                new Driver(
                        new DriverName(form.name),
                        new TeamName(form.teamName),
                        new Nationality(form.nationality),
                        new RacingNumber(form.racingNumber),
                        new Money(form.salaryCurrency, form.salary),
                        new ChampionshipPoints(form.championshipPoints)
                )
        );
    }

    @Override
    public Driver update(DriverId id, String name, String teamName, String nationality,
                         Integer racingNumber, BigDecimal salary,
                         Currency salaryCurrency, Integer championshipPoints) {
        Driver driver = findById(id);
        driver.updateProfile(
                new DriverName(name),
                new TeamName(teamName),
                new Nationality(nationality),
                new RacingNumber(racingNumber),
                new Money(salaryCurrency, salary),
                new ChampionshipPoints(championshipPoints)
        );
        return driverRepository.save(driver);
    }

    @Override
    public Driver delete(DriverId id) {
        Driver driver = findById(id);
        driverRepository.delete(driver);
        return driver;
    }

    @Override
    public Driver addPoints(DriverId id, Integer points) {
        Driver driver = findById(id);
        driver.addPoints(new ChampionshipPoints(points));
        return driverRepository.save(driver);
    }
}
