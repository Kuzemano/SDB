package r.real.users.domain.valueObjects;

import jakarta.persistence.Embeddable;

@Embeddable
public class Password {
    private String password;

    protected Password() {}

    public Password(String password) {
        if (password == null || password.isBlank())
            throw new IllegalArgumentException("Password must not be blank");
        this.password = password;
    }

    public String getPassword() { return password; }

    @Override
    public String toString() { return "[PROTECTED]"; }
}