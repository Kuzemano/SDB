package r.real.service;

import r.real.model.Driver;
import r.real.model.*;
import r.real.model.form.DriverForm;

import java.util.List;

public interface DriverService {

    List<Driver> listAll();

    Driver findById(DriverId id);

    List<Driver> findByName(String name);

    List<Driver> findByTeam(String teamName);

    Driver create(String name, String teamName, String nationality,
                  Integer racingNumber, java.math.BigDecimal salary,
                  r.real.model.valueObjects.Currency salaryCurrency,
                  Integer championshipPoints);

    Driver create(DriverForm form);

    Driver update(DriverId id, String name, String teamName, String nationality,
                  Integer racingNumber, java.math.BigDecimal salary,
                  r.real.model.valueObjects.Currency salaryCurrency,
                  Integer championshipPoints);

    Driver delete(DriverId id);

    Driver addPoints(DriverId id, Integer points);
}
