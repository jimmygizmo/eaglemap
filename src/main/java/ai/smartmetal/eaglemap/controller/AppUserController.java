package ai.smartmetal.eaglemap.controller;

import ai.smartmetal.eaglemap.model.AppUser;
import ai.smartmetal.eaglemap.repository.AppUserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class AppUserController {
    private final AppUserRepository repo;

    public AppUserController(AppUserRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<AppUser> all() {
        return repo.findAll();
    }

    // Lowercase appuser was previously user. I assumed it also needed to change.
    @PostMapping
    public AppUser create(@RequestBody AppUser appuser) {
        return repo.save(appuser);
    }
}
