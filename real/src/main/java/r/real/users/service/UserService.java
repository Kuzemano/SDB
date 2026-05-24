package r.real.users.service;




import r.real.users.domain.User;
import r.real.users.domain.valueObjects.Role;

public interface UserService {
    User create(String username, String password, Role role);
}