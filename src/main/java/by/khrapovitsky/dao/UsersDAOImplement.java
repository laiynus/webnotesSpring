package by.khrapovitsky.dao;

import by.khrapovitsky.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsersDAOImplement implements UsersDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void delete(User user) {
       sessionFactory.getCurrentSession().delete(user);
    }

    public void insert(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    public void update(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    public List getAllUsers() {
        return sessionFactory.getCurrentSession().createCriteria(User.class).list();
    }

    public User getUser(String login) {
        return (User) sessionFactory.getCurrentSession().get(User.class, login);
    }
}
