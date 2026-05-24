package r.real.model.valueObjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import r.real.model.base.ValueObject;

import static org.apache.commons.lang3.Validate.notNull;

@Embeddable
public class DriverTag implements ValueObject {

    @Column(name = "tag_name", nullable = false)
    private String tagName;

    public DriverTag(String tagName) {
        this.tagName = notNull(tagName, "tagName must not be null");
    }

    protected DriverTag() {
    }

    public String getTagName() {
        return tagName;
    }
}