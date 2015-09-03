package by.khrapovitsky.service;

import by.khrapovitsky.dao.NotesDAO;
import by.khrapovitsky.model.Note;
import by.khrapovitsky.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NotesServiceImplement implements NotesService {

    @Autowired
    private NotesDAO notesDAO;

    @Override
    public void delete(Note note) {
        notesDAO.delete(note);
    }

    @Override
    public void insert(Note note) {
        notesDAO.insert(note);
    }

    @Override
    public void update(Note note) {
        notesDAO.update(note);
    }

    @Override
    public List getAllNotes() {
        return notesDAO.getAllNotes();
    }

    @Override
    public Note getNote(int id) {
        return notesDAO.getNote(id);
    }

    @Override
    public Note getNoteWithUser(int id) {
        return notesDAO.getNoteWithUser(id);
    }

    @Override
    public List getLastUserNotes(User user) {
        return notesDAO.getLastUserNotes(user);
    }

    @Override
    public List getUserNotes(User user) {
        return notesDAO.getUserNotes(user);
    }
}
