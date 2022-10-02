package io.multiverse.journal;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class JournalController {
    private JournalRepo repo;
    private UserRepo userrepo;

    public JournalController(JournalRepo repo, UserRepo userrepo) {
        this.repo = repo;
        this.userrepo = userrepo;
    }

    @GetMapping("/journals")
    public String getJournals(Model model) {
        List<User> users = userrepo.findAll();
        model.addAttribute("users", users);
        return "journals";
    }

    @PostMapping("/users/{id}/journals")
    public void createJournal(@RequestBody JournalEntry journal, @PathVariable Integer id, HttpServletResponse res) throws Exception {
        Optional<User> user = userrepo.findById(id);
        if (user.isPresent()) {
            journal.setUser(user.get());
        }
        this.repo.save(journal);
        res.sendRedirect("/journals");
    }
}
