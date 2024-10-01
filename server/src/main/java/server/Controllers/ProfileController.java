package server.Controllers;

import java.util.List;
import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import server.Daos.UserDao;
import server.Models.User;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class ProfileController {
    private UserDao userDao;

    public ProfileController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("")
    public User getCurrentUser(Principal principal) {
        return userDao.getUserByUsername(principal.getName());
    }

    @GetMapping("/roles")
    public List<String> getCurrentUserRoles(Principal principal) {
        return userDao.getUserRoles(principal.getName());
    }
}
