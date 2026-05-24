package r.real.model;

import jakarta.persistence.*;
import r.real.model.base.AbstractEntity;
import r.real.model.valueObjects.DriverCandidateState;
import r.real.model.valueObjects.DriverName;
import r.real.model.valueObjects.Nationality;

import static org.apache.commons.lang3.Validate.notNull;

@Entity
@Table(name = "driver_candidates")
public class DriverCandidate extends AbstractEntity<DriverCandidateId> {

    @Embedded
    private DriverName name;

    @Embedded
    private Nationality nationality;

    @Embedded
    private DriverCandidateState state;

    public DriverCandidate(DriverName name, Nationality nationality) {
        super(new DriverCandidateId());
        this.name = notNull(name, "name must not be null");
        this.nationality = notNull(nationality, "nationality must not be null");
        this.state = DriverCandidateState.created();
    }

    protected DriverCandidate() {
        super();
    }

    public DriverName getName() {
        return name;
    }

    public Nationality getNationality() {
        return nationality;
    }

    public DriverCandidateStatus getStatus() {
        return state.getStatus();
    }

    public void shortlist() {
        state.shortlist();
    }

    public void approve() {
        state.approve();
    }

    public void reject() {
        state.reject();
    }

    public void sign() {
        state.sign();
    }
}