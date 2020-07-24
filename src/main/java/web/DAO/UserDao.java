package web.DAO;


import web.model.Role;
import web.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);
    List<User> listUsers();
    User findById (Long id);
    User findByLogin (String login);
    void deleteUser(Long id);
    void update(User user);
    public Role getRole(Long id);
}
