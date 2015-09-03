package by.khrapovitsky.dao;

import by.khrapovitsky.model.Note;
import by.khrapovitsky.model.User;

import java.util.List;

public interface NotesDAO {
    void delete(Note note);
    void insert(Note note);
    void update(Note note);
    List getAllNotes();
    Note getNote(int id);
    Note getNoteWithUser(int id);
    List getLastUserNotes(User user);
    List getUserNotes(User user);
}
