package r.real.drivers.domain.valueObjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import r.real.common.domain.base.ValueObject;

import static org.apache.commons.lang3.Validate.*;

@Embeddable
public class RacingNumber implements ValueObject {

    @Column(name = "racing_number", nullable = false)
    private Integer value;

    public RacingNumber(Integer value) {
        notNull(value, "Racing number must not be null");
        inclusiveBetween(1, 99, value, "Racing number must be between 1 and 99");
        this.value = value;
    }

    protected RacingNumber() { }

    public Integer getValue() {
        return value;
    }
}