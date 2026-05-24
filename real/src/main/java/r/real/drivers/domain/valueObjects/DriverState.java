package r.real.drivers.domain.valueObjects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import r.real.drivers.domain.DriverStatus;

import static org.apache.commons.lang3.Validate.validState;

@Embeddable
public class DriverState {

    @Enumerated(EnumType.STRING)
    private DriverStatus status;

    protected DriverState() {
    }

    public DriverState(DriverStatus status) {
        this.status = status;
    }

    public static DriverState signed() {
        return new DriverState(DriverStatus.SIGNED);
    }

    public DriverStatus getStatus() {
        return status;
    }

    public void activate() {
        validState(status == DriverStatus.SIGNED || status == DriverStatus.SUSPENDED,
                "Driver can only be activated from SIGNED or SUSPENDED");
        status = DriverStatus.ACTIVE;
    }

    public void suspend() {
        validState(status == DriverStatus.ACTIVE,
                "Driver can only be suspended from ACTIVE");
        status = DriverStatus.SUSPENDED;
    }

    public void retire() {
        validState(status == DriverStatus.ACTIVE || status == DriverStatus.SUSPENDED,
                "Driver can only retire from ACTIVE or SUSPENDED");
        status = DriverStatus.RETIRED;
    }

    public void ensureCanUpdateContractData() {
        validState(status != DriverStatus.RETIRED,
                "Retired driver data cannot be modified");
    }
}