package r.real.drivers.domain.valueObjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import r.real.common.domain.base.ValueObject;

import static org.apache.commons.lang3.Validate.*;

@Embeddable
public class ChampionshipPoints implements ValueObject {

    @Column(name = "championship_points", nullable = false)
    private Integer value;

    public ChampionshipPoints(Integer value) {
        notNull(value, "Championship points must not be null");
        inclusiveBetween(0, 5000, value, "Championship points must be between 0 and 5000");
        this.value = value;
    }

    protected ChampionshipPoints() { }

    public ChampionshipPoints add(ChampionshipPoints other) {
        notNull(other, "Championship points must not be null");
        return new ChampionshipPoints(this.value + other.value);
    }

    public Integer getValue() {
        return value;
    }
}