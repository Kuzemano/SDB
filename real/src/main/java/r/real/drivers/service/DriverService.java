package r.real.drivers.service;

import r.real.drivers.domain.Driver;
import r.real.drivers.domain.ids.DriverId;
import r.real.common.domain.valueObjects.Currency;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

public interface DriverService {
    List<Driver> listAll();
    Driver findById(DriverId id);
    Driver create(String name, String teamName, String nationality, Integer racingNumber,
                  BigDecimal salary, Currency salaryCurrency, Integer championshipPoints);

    Driver create(String name, String teamName, String nationality, Integer racingNumber,
                  BigDecimal salary, Currency salaryCurrency, Integer championshipPoints,
                  ZonedDateTime contractStartDate, ZonedDateTime contractEndDate);

    Driver update(DriverId id, String name, String teamName, String nationality,
                  Integer racingNumber, BigDecimal salary, Currency salaryCurrency, Integer championshipPoints);

    Driver delete(DriverId id);
    Driver addPoints(DriverId id, Integer points);
    List<Driver> findByName(String name);
    List<Driver> findByTeam(String team);

    Driver signCandidate(String candidateId,
                         String teamName,
                         Integer racingNumber,
                         BigDecimal salary,
                         Currency salaryCurrency,
                         ZonedDateTime contractStartDate,
                         ZonedDateTime contractEndDate);
}