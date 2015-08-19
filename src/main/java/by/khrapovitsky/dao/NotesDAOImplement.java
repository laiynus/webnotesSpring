package by.khrapovitsky.dao;

import by.khrapovitsky.model.Note;
import by.khrapovitsky.model.User;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class NotesDAOImplement implements NotesDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void delete(Note note) {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();;
            session.beginTransaction();
            session.delete(note);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void insert(Note note) {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();;
            session.beginTransaction();
            session.save(note);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void update(Note note) {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();;
            session.beginTransaction();
            session.update(note);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public List<Note> getAllNotes() {
        Session session = null;
        List notes = new ArrayList();
        try {
            session = sessionFactory.getCurrentSession();;
            notes = session.createCriteria(Note.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return notes;
    }

    public Note getNote(int id) {
        Session session = null;
        Note note = null;
        try {
            session = sessionFactory.getCurrentSession();;
            note = (Note) session.get(Note.class, id);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return note;
    }

    public Note getNoteWithUser(int id) {
        Session session = null;
        Note note = null;
        try {
            session = sessionFactory.getCurrentSession();;
            note = (Note) session.get(Note.class, id);
            Hibernate.initialize(note.getUser());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return note;
    }

    public List<Note> getLastUserNotes(User user) {
        Session session = null;
        List notes = new ArrayList();
        try {
            session = sessionFactory.getCurrentSession();;
            notes = session.createCriteria(Note.class).add(Restrictions.like("user", user)).addOrder(Order.desc("dateTimeCreate")).setMaxResults(10).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return notes;
    }

    public List<Note> getUserNotes(User user) {
        Session session = null;
        List notes = new ArrayList();
        try {
            session = sessionFactory.getCurrentSession();;
            notes = session.createCriteria(Note.class).add(Restrictions.like("user", user)).addOrder(Order.desc("dateTimeCreate")).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return notes;
    }
}
