package server.Daos;

import server.Models.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    User getUserByUsername(String username);

    void createUser(User user);

    void updatePassword(User user);

    void updateUserWithoutPassword(User user);

    void deleteUser(String username);

    List<String> getUserRoles(String username);

    void addRoleToUser(String username, String role);

    void removeRoleFromUser(String username, String role);
}
