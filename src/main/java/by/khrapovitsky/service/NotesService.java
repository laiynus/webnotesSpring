package by.khrapovitsky.service;

import by.khrapovitsky.model.Note;
import by.khrapovitsky.model.User;

import java.util.List;

public interface NotesService {
    void delete(Note note);
    void insert(Note note);
    void update(Note note);
    List<Note> getAllNotes();
    Note getNote(int id);
    Note getNoteWithUser(int id);
    List<Note> getLastUserNotes(User user);
    List<Note> getUserNotes(User user);
}
