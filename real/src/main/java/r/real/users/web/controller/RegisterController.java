package r.real.users.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import r.real.users.domain.valueObjects.Role;
import r.real.users.service.UserService;

@Controller

public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService){
        this.userService = userService;
    }


    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password) {
        userService.create(username, password, Role.ROLE_USER);
        return "redirect:/login";
    }
}
