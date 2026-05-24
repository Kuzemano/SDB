package r.real.service.impl;

import org.springframework.stereotype.Service;
import r.real.model.Driver;
import r.real.model.exceptions.InvalidDriverIdException;
import r.real.repository.DriverRepository;
import r.real.service.DriverService;

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
    public Driver findById(Long id) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new InvalidDriverIdException(id));
    }

    @Override
    public List<Driver> findByName(String name) {
        return driverRepository.findByName(name);
    }

    @Override
    public List<Driver> findByTeam(String teamName) {
        return driverRepository.findDriverByTeamName(teamName);
    }

    @Override
    public Driver create(String name, String teamName, String nationality,
                         Integer racingNumber, Double salary, String salaryCurrency,
                         Integer championshipPoints) {
        Driver driver = new Driver(name, teamName, nationality,
                racingNumber, salary, salaryCurrency, championshipPoints);
        return driverRepository.save(driver);
    }

    @Override
    public Driver update(Long id, String name, String teamName, String nationality,
                         Integer racingNumber, Double salary, String salaryCurrency,
                         Integer championshipPoints) {
        Driver driver = findById(id);
        driver.setName(name);
        driver.setTeamName(teamName);
        driver.setNationality(nationality);
        driver.setRacingNumber(racingNumber);
        driver.setSalary(salary);
        driver.setSalaryCurrency(salaryCurrency);
        driver.setChampionshipPoints(championshipPoints);
        return driverRepository.save(driver);
    }

    @Override
    public Driver delete(Long id) {
        Driver driver = findById(id);
        driverRepository.delete(driver);
        return driver;
    }

    @Override
    public Driver addPoints(Long id, Integer points) {
        Driver driver = findById(id);
        driver.setChampionshipPoints(driver.getChampionshipPoints() + points);
        return driverRepository.save(driver);
    }
}
