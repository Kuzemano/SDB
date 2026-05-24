package r.real.service;

import r.real.model.Driver;

import java.util.List;

public interface DriverService {

    List<Driver> listAll();

    Driver findById(Long id);

    List<Driver> findByName(String name);

    List<Driver> findByTeam(String teamName);

    Driver create(String name, String teamName, String nationality,
                  Integer racingNumber, Double salary, String salaryCurrency,
                  Integer championshipPoints);

    Driver update(Long id, String name, String teamName, String nationality,
                  Integer racingNumber, Double salary, String salaryCurrency,
                  Integer championshipPoints);

    Driver delete(Long id);

    Driver addPoints(Long id, Integer points);
}
