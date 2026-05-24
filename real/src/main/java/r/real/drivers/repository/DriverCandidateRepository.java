package r.real.drivers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import r.real.drivers.domain.DriverCandidate;
import r.real.drivers.domain.ids.DriverCandidateId;

public interface DriverCandidateRepository extends JpaRepository<DriverCandidate, DriverCandidateId> {
}