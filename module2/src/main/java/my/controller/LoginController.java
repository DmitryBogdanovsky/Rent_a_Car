package my.controller;

import lombok.RequiredArgsConstructor;
import my.dto.UserDetailsDto;
import my.dto.UserDto;
import my.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LoginController {

    private final UserService userService;

    @GetMapping("/login.view")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/registration.action")
    public String regUserForm(Model model) {
        model.addAttribute("user", new UserDto(new UserDetailsDto()));
        return "login";
    }

    @PostMapping(value = "registration.action")
    public String registrationUser(@ModelAttribute("user") UserDto userDto) {
        userService.addUser(userDto);
        System.out.println("User registration was successfully ........");
        return "login";
    }
}