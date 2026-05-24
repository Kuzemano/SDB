package r.real.model.base;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import static org.apache.commons.lang3.Validate.*;


@Embeddable

public class DomainObjectId implements Serializable {

    @Column(name = "id", nullable = false, updatable = false)
    private String id;

    protected DomainObjectId(String uuid) {
        notNull(uuid, "uuid must not be null");
        try {
            this.id = UUID.fromString(uuid.trim()).toString();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("The UUID must be in a valid format");
        }
    }

    protected DomainObjectId() {
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
        if (!(o instanceof DomainObjectId that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
