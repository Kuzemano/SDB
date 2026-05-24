package r.real.rides.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import r.real.common.domain.base.AbstractEntity;
import r.real.drivers.domain.ids.DriverId;
import r.real.rides.domain.ids.RideId;


import static org.apache.commons.lang3.Validate.notNull;

@Entity
@Getter
@NoArgsConstructor
public class Ride extends AbstractEntity<RideId> {

    @Embedded
    private DriverId driverId;

    public Ride(DriverId driverId) {
        super(new RideId());
        this.driverId = notNull(driverId, "driverId must not be null");
    }
}