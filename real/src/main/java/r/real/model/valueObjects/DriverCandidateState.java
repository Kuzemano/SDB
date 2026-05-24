package r.real.model.valueObjects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import r.real.model.DriverCandidateStatus;

import static org.apache.commons.lang3.Validate.validState;

@Embeddable
public class DriverCandidateState {

    @Enumerated(EnumType.STRING)
    private DriverCandidateStatus status;

    protected DriverCandidateState() {
    }

    public DriverCandidateState(DriverCandidateStatus status) {
        this.status = status;
    }

    public static DriverCandidateState created() {
        return new DriverCandidateState(DriverCandidateStatus.CREATED);
    }

    public DriverCandidateStatus getStatus() {
        return status;
    }

    public void shortlist() {
        validState(status == DriverCandidateStatus.CREATED,
                "Candidate can only be shortlisted from CREATED");
        status = DriverCandidateStatus.SHORTLISTED;
    }

    public void approve() {
        validState(status == DriverCandidateStatus.SHORTLISTED,
                "Candidate can only be approved from SHORTLISTED");
        status = DriverCandidateStatus.APPROVED;
    }

    public void reject() {
        validState(status == DriverCandidateStatus.CREATED || status == DriverCandidateStatus.SHORTLISTED,
                "Candidate can only be rejected from CREATED or SHORTLISTED");
        status = DriverCandidateStatus.REJECTED;
    }

    public void sign() {
        validState(status == DriverCandidateStatus.APPROVED,
                "Candidate can only be signed from APPROVED");
        status = DriverCandidateStatus.SIGNED;
    }
}