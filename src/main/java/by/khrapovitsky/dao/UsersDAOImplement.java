package by.khrapovitsky.dao;

import by.khrapovitsky.model.User;
import by.khrapovitsky.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;
import java.util.ArrayList;

public class UsersDAOImplement implements UsersDAO {

    public void delete(User user) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void insert(User user) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {

                session.close();
            }
        }
    }

    public void update(User user) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public List<User> getAllUsers() {
        Session session = null;
        List users = new ArrayList();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            users = session.createCriteria(User.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return users;
    }

    public User getUser(String login) {
        Session session = null;
        User user = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            user = (User) session.get(User.class, login);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return user;
    }
}
