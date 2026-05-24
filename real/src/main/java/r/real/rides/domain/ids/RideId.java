package r.real.rides.domain.ids;

import r.real.common.domain.base.DomainObjectId;

public class RideId extends DomainObjectId {
    public RideId() {
        super();
    }

    public RideId(String uuid) {
        super(uuid);
    }
}