package by.khrapovitsky.service;

import by.khrapovitsky.dao.UsersDAO;
import by.khrapovitsky.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UsersServiceImplement implements UsersService, UserDetailsService{

    @Autowired
    private UsersDAO usersDAO;

    @Override
    public void delete(User user) {
        usersDAO.delete(user);
    }

    @Override
    public void insert(User user) {
        usersDAO.insert(user);
    }

    @Override
    public void update(User user) {
        usersDAO.update(user);
    }

    @Override
    public List getAllUsers() {
        return usersDAO.getAllUsers();
    }

    @Override
    public User getUser(String login) {
        return usersDAO.getUser(login);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return findByUserName(s);
    }

    public User findByUserName(String login){
        return usersDAO.getUser(login);
    }
}
