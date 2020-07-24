package web.service;

import web.model.Role;
import web.model.User;

import java.util.List;

public interface UserService  {
    boolean add(User user);
    List<User> listUsers();
    User findById (Long id);
    void deleteUser(Long id);
    public void update(User user);
    public User getUser(String login);
    public Role getRole(Long id);
}
