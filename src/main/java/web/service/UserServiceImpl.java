package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.DAO.UserDao;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserDao userDao;

    @PersistenceContext
    private EntityManager entityManager;


    @Transactional
    @Override
    public boolean add(User user) {
        User newUser = userDao.findByLogin(user.getName());

        if (newUser != null) {
            return false;
        }
        user.setRoles(Collections.singleton(new Role(1l, "ROLE_USER")));
        user.setPassword(user.getPassword());
        userDao.add(user);
        return true;
    }

    @Transactional
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Transactional
    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    @Transactional
    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Transactional
    @Override
    public User getUser(String login) {
        User user = new User();
        user.setName(login);
        user.setPassword("7110eda4d09e062aa5e4a390b0a572ac0d2c0220");

        return user;
    }

    @Transactional
    @Override
    public Role getRole(Long id) {
        Role role = userDao.getRole(id);
        return role;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.findByLogin(s);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }


//    public UserDetails loadUserByUsernameRepo(String s) throws UsernameNotFoundException {
//        User user = userRepository.findUserByName(s);
//
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
//        return user;
//    }
}
