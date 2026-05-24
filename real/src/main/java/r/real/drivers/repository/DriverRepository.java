package r.real.drivers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import r.real.drivers.domain.Driver;
import r.real.drivers.domain.ids.DriverId;

import java.util.List;

public interface DriverRepository extends JpaRepository<Driver, DriverId> {
    List<Driver> findByNameValue(String name);
    List<Driver> findByTeamNameValue(String teamName);
}