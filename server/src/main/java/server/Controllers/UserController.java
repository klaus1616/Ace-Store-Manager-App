package server.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import server.Daos.UserDao;
import server.Models.User;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * User controller
 */

@RestController
@RequestMapping("/api/users")
@CrossOrigin

/**
 * Constructor
 *
 * @param userDao user data access object
 */

public class UserController {
    private UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("")
    public String requestMethodName(@RequestParam String param) {
        return new String();
    }

    /**
     * Get all users
     *
     * @return list of users
     */

    public List<User> listUsers() {
        return userDao.getAllUsers();
    }

    /**
     * Get user by username
     *
     * @param username username
     * @return user
     */

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userDao.getUserByUsername(username);
    }

    /**
     * Create user
     *
     * @param user user
     * @return created user
     */

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody User user) {
        userDao.createUser(user);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{username}")
    public void updateUserWithoutPassword(@RequestBody User User) {
        userDao.updateUserWithoutPassword(User);
    }

    /**
     * Update user
     *
     * @param username username
     * @return updated user
     */

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/password/{username}")
    public void updateUser(@PathVariable String username, @RequestBody User User) {
        User.setUsername(username);
        userDao.updatePassword(User);
    }

    /**
     * Delete user
     *
     * @param username username
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserByUsername(@PathVariable String username) {
        userDao.deleteUser(username);
    }

    /**
     * Get user roles
     *
     * @param username username
     * @return list of roles
     */

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{username}/roles")
    public List<String> getUserRoles(@PathVariable String username) {
        return userDao.getUserRoles(username);
    }

    /**
     * Add user role
     *
     * @param username username
     * @param role role
     */

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{username}/roles/{role}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addRoleToUser(@PathVariable String username, @PathVariable String role) {
        userDao.addRoleToUser(username, role);
    }

    /**
     * Remove user role
     *
     * @param username username
     * @param role role
     */

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{username}/roles/{role}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeRoleFromUser(@PathVariable String username, @PathVariable String role) {
        userDao.removeRoleFromUser(username, role);
    }
}
