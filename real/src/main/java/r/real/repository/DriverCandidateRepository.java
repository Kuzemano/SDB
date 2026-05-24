package r.real.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import r.real.model.DriverCandidate;
import r.real.model.DriverCandidateId;

public interface DriverCandidateRepository extends JpaRepository<DriverCandidate, DriverCandidateId> {
}