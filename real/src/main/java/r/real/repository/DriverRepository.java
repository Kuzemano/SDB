package r.real.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import r.real.model.Driver;
import r.real.model.DriverId;

import java.util.List;

public interface DriverRepository extends JpaRepository<Driver, DriverId> {
    List<Driver> findByNameValue(String name);
    List<Driver> findByTeamNameValue(String teamName);
}