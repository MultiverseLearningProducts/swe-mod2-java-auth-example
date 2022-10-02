package io.multiverse.journal;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {
    private UserRepo userrepo;

    public UserController(UserRepo userrepo) {
        this.userrepo = userrepo;
    }

    @GetMapping("/users")
    public String getUsers (Model model) {
        List<User> users = this.userrepo.findAll();
        model.addAttribute("users", users);
        return "home";
    }

    @PostMapping("/register")
    public void createUser(User user, HttpServletResponse res) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole("USER");
        user.setEnabled(true);
        this.userrepo.save(user);
        res.sendRedirect("/login");
    }
}
