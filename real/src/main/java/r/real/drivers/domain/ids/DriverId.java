package r.real.drivers.domain.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import static org.apache.commons.lang3.Validate.notNull;

@Embeddable
public class DriverId implements Serializable {

    @Column(name = "id", nullable = false, updatable = false)
    private String id;

    public DriverId(String id) {
        notNull(id, "id must not be null");
        try {
            this.id = UUID.fromString(id.trim()).toString();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("The UUID must be in a valid format");
        }
    }

    public DriverId() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DriverId driverId)) return false;
        return Objects.equals(id, driverId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}