package r.real.users.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import r.real.users.domain.User;
import r.real.users.domain.valueObjects.Role;
import r.real.users.repository.UserRepository;
import r.real.users.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User create(String username, String password, Role role) {
        if (password == null || password.isBlank())
            throw new IllegalArgumentException("Password must not be blank");
        String encoded = passwordEncoder.encode(password);
        User user = new User(username, encoded, role);
        return userRepository.save(user);
    }


}