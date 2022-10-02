package io.multiverse.journal;

import java.security.Principal;
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
    public String getJournals(Model model, Principal principal) {
        User user = userrepo.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "journals";
    }

    @PostMapping("/journals")
    public void createJournal(JournalEntry journal, HttpServletResponse res, Principal principal) throws Exception {
        User user = userrepo.findByUsername(principal.getName());
        journal.setUser(user);
        this.repo.save(journal);
        res.sendRedirect("/journals");
    }
}
