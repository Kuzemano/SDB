package r.real.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import static org.apache.commons.lang3.Validate.notNull;

@Embeddable
public class DriverCandidateId implements Serializable {

    @Column(name = "id", nullable = false, updatable = false)
    private String id;

    public DriverCandidateId(String id) {
        notNull(id, "id must not be null");
        try {
            this.id = UUID.fromString(id.trim()).toString();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("The UUID must be in a valid format");
        }
    }

    public DriverCandidateId() {
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
        if (!(o instanceof DriverCandidateId that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}