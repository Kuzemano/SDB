package r.real.users.domain.valueObjects;



import jakarta.persistence.Embeddable;

@Embeddable
public class Username {
    private String username;

    protected Username() {}

    public Username(String username) {
        if (username == null || username.isBlank())
            throw new IllegalArgumentException("Username must not be blank");
        if (username.length() < 3 || username.length() > 50)
            throw new IllegalArgumentException("Username must be 3–50 characters");
        if (!username.matches("^[a-zA-Z0-9._-]+$"))
            throw new IllegalArgumentException("Username contains invalid characters");
        this.username = username;
    }

    public String getUsername() { return username; }

    @Override
    public String toString() { return username; }
}