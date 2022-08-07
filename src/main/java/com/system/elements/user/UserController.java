package com.system.elements.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserRepository repoUser;

    @GetMapping("")
    private String viewHomePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = repoUser.findUserByEmail(email);
            model.addAttribute("user", user);
            return "manageAccount";
        }
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/login")
    private String viewLoginPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = repoUser.findUserByEmail(email);
            model.addAttribute("user", user);
            return "manageAccount";
        }
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/register")
    private String viewRegisterPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = repoUser.findUserByEmail(email);
            model.addAttribute("user", user);
            return "manageAccount";
        }
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    private String processRegister(User user, Model model, @RequestParam("phoneNumber") int phoneNumber) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        String error = "correctRegister";
        user.setPhoneNumber(phoneNumber);
        try {
            repoUser.save(user);
        } catch (Exception e) {
            error = "errorRegister";
        }
        model.addAttribute("error", error);
        model.addAttribute("user", new User());
        return "register";
    }

    @GetMapping("/manageAccount")
    private String viewManageAccountPage (Model model) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = repoUser.findUserByEmail(email);
        model.addAttribute("user", user);
        return "manageAccount";
    }

    @PostMapping("/manageAccount")
    private String processManageAccount(Model model, User user, @RequestParam("password") String password) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User logged = repoUser.findUserByEmail(email);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(password, logged.getPassword())) {
            logged.setFirstName(user.getFirstName());
            logged.setLastName(user.getLastName());
            logged.setEmail(user.getEmail());
            logged.setPhoneNumber(user.getPhoneNumber());
            logged.setAddress(user.getAddress());
            logged.setCity(user.getCity());

            try {
                repoUser.save(logged);
            } catch (Exception e) {

            }
        }
        model.addAttribute("user", logged);
        return "manageAccount";
    }
}
