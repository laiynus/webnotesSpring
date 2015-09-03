package by.khrapovitsky.dao;

import by.khrapovitsky.model.Note;
import by.khrapovitsky.model.User;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class NotesDAOImplement implements NotesDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void delete(Note note) {
        sessionFactory.getCurrentSession().delete(note);
    }

    public void insert(Note note) {
        sessionFactory.getCurrentSession().save(note);
    }

    public void update(Note note) {
        sessionFactory.getCurrentSession().update(note);
    }

    public List getAllNotes() {
        return sessionFactory.getCurrentSession().createCriteria(Note.class).list();
    }

    public Note getNote(int id) {
        return (Note) sessionFactory.getCurrentSession().get(Note.class, id);
    }

    public Note getNoteWithUser(int id) {
        Note note = (Note) sessionFactory.getCurrentSession().get(Note.class,id);
        Hibernate.initialize(note.getUser());
        return note;
    }

    public List getLastUserNotes(User user) {
        return sessionFactory.getCurrentSession().createCriteria(Note.class).add(Restrictions.like("user", user)).addOrder(Order.desc("dateTimeCreate")).setMaxResults(10).list();
    }

    public List getUserNotes(User user) {
        return sessionFactory.getCurrentSession().createCriteria(Note.class).add(Restrictions.like("user", user)).addOrder(Order.desc("dateTimeCreate")).list();
    }
}
