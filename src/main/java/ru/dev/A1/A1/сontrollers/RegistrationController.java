package ru.dev.A1.A1.сontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dev.A1.A1.dao.JpaDAOHibernate;
import ru.dev.A1.A1.data.UserRepository;
import ru.dev.A1.A1.models.RegistrationForm;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    //private UserRepository userRepo;
    private JpaDAOHibernate repo;
    private PasswordEncoder passwordEncoder;
    public RegistrationController(
            JpaDAOHibernate repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }
    @GetMapping
    public String registerForm() {
        return "registration";
    }
    @PostMapping
    public String processRegistration(RegistrationForm form) {
        repo.insertUser(form.toUser(passwordEncoder));
        return "redirect:/login";
    }
}
