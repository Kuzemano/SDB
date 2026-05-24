package r.real.drivers.domain.valueObjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import r.real.common.domain.base.ValueObject;

import java.util.regex.Pattern;

import static org.apache.commons.lang3.Validate.*;

@Embeddable
public class DriverName implements ValueObject {

    private static final Pattern SQL_INJECTION_PATTERN =
            Pattern.compile("(?i).*(--|\\b(select|insert|update|delete|drop|union|or|and)\\b).*");

    @Column(name = "driver_name", nullable = false)
    private String value;

    public DriverName(String value) {
        notNull(value, "Driver name must not be null");
        isTrue(value.length() >= 2 && value.length() <= 100,
                "Driver name must be 2-100 characters long");
        matchesPattern(value, "^[A-Za-z .'-]{2,100}$",
                "Driver name must be 2-100 characters and contain only letters, spaces, apostrophes, dots or hyphens");
        isTrue(!SQL_INJECTION_PATTERN.matcher(value).matches(),
                "Driver name contains illegal patterns");

        this.value = value;
    }

    protected DriverName() {
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DriverName that = (DriverName) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}