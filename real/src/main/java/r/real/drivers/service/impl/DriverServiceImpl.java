package r.real.drivers.service.impl;

import org.springframework.stereotype.Service;
import r.real.common.domain.valueObjects.Currency;
import r.real.common.domain.valueObjects.Money;
import r.real.drivers.domain.valueObjects.*;
import r.real.drivers.domain.Driver;
import r.real.drivers.domain.DriverCandidate;
import r.real.drivers.domain.ids.DriverCandidateId;
import r.real.drivers.domain.ids.DriverId;
import r.real.drivers.domain.exceptions.InvalidDriverIdException;
import r.real.drivers.repository.DriverCandidateRepository;
import r.real.drivers.repository.DriverRepository;
import r.real.drivers.service.DriverService;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final DriverCandidateRepository driverCandidateRepository;
    public DriverServiceImpl(DriverRepository driverRepository, DriverCandidateRepository driverCandidateRepository) {
        this.driverRepository = driverRepository;
        this.driverCandidateRepository = driverCandidateRepository;
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
    public Driver create(String name, String teamName, String nationality, Integer racingNumber,
                         BigDecimal salary, Currency salaryCurrency, Integer championshipPoints) {
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
    public Driver create(String name, String teamName, String nationality, Integer racingNumber,
                         BigDecimal salary, Currency salaryCurrency, Integer championshipPoints,
                         ZonedDateTime contractStartDate, ZonedDateTime contractEndDate) {

        Driver driver = new Driver.Builder(
                new DriverName(name),
                new TeamName(teamName),
                new Nationality(nationality),
                new RacingNumber(racingNumber),
                new Money(salaryCurrency, salary),
                new ChampionshipPoints(championshipPoints)
        )
                .withContractStartDate(contractStartDate)
                .withContractEndDate(contractEndDate)
                .build();

        return driverRepository.save(driver);
    }

    @Override
    public Driver update(DriverId id, String name, String teamName, String nationality,
                         Integer racingNumber, BigDecimal salary, Currency salaryCurrency, Integer championshipPoints) {
        Driver driver = findById(id);
        driver.updateDriverName(new DriverName(name));
        driver.updateTeamName(new TeamName(teamName));
        driver.updateNationality(new Nationality(nationality));
        driver.updateRacingNumber(new RacingNumber(racingNumber));
        driver.updateSalary(new Money(salaryCurrency, salary));
        driver.updateChampionshipPoints(new ChampionshipPoints(championshipPoints));
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

    @Override
    public List<Driver> findByName(String name) {
        return driverRepository.findByNameValue(name);
    }

    @Override
    public List<Driver> findByTeam(String team) {
        return driverRepository.findByTeamNameValue(team);
    }

    @Override
    public Driver signCandidate(String candidateId,
                                String teamName,
                                Integer racingNumber,
                                BigDecimal salary,
                                Currency salaryCurrency,
                                ZonedDateTime contractStartDate,
                                ZonedDateTime contractEndDate) {

        DriverCandidate candidate = driverCandidateRepository.findById(new DriverCandidateId(candidateId))
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        candidate.sign();

        Driver driver = Driver.fromSignedCandidate(
                candidate,
                new TeamName(teamName),
                new RacingNumber(racingNumber),
                new Money(salaryCurrency, salary),
                contractStartDate,
                contractEndDate
        );

        driverCandidateRepository.save(candidate);
        return driverRepository.save(driver);
    }
}