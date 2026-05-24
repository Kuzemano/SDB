package r.real.drivers.domain.valueObjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import r.real.common.domain.base.ValueObject;

import static org.apache.commons.lang3.Validate.*;

@Embeddable
public class TeamName implements ValueObject {

    @Column(name = "team_name", nullable = false)
    private String value;

    public TeamName(String value) {
        notNull(value, "Team name must not be null");
        matchesPattern(value, "^[A-Za-z0-9 &.'-]{2,100}$",
                "Team name must be 2-100 characters long");
        this.value = value;
    }

    protected TeamName() { }

    public String getValue() {
        return value;
    }
}