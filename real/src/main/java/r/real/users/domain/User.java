package r.real.users.domain;


import jakarta.persistence.*;
import r.real.common.domain.base.AbstractEntity;
import r.real.users.domain.ids.UserId;
import r.real.users.domain.valueObjects.Password;
import r.real.users.domain.valueObjects.Role;
import r.real.users.domain.valueObjects.Username;

@Entity
@Table(name = "app_user")
public class User extends AbstractEntity<UserId> {

    @Embedded
    private Username username;

    @Embedded
    private Password password;

    @Enumerated(EnumType.STRING)
    private Role role;

    protected User() { super(new UserId()); }

    public User(String username, String encodedPassword, Role role) {
        super(new UserId());
        this.username = new Username(username);
        this.password = new Password(encodedPassword);
        this.role = role;
    }

    public String getUsername() { return username.getUsername(); }
    public String getPassword() { return password.getPassword(); }
    public Role getRole() { return role; }
}