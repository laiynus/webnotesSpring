package by.khrapovitsky.controller;

import by.khrapovitsky.model.Note;
import by.khrapovitsky.model.User;
import by.khrapovitsky.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
public class NotesController {

    @Autowired
    private NotesService notesService;

    @RequestMapping(value = {"/notes"}, method = RequestMethod.GET)
    public ModelAndView defaultPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("notes");
        return model;
    }

    @RequestMapping(value = "addNote", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Note addNote(@RequestBody Note noteText) throws IOException {
        Note note = null;
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            note = new Note((new User(SecurityContextHolder.getContext().getAuthentication().getName(), null)), noteText.getNote(), new Timestamp(new java.util.Date().getTime()));
            notesService.insert(note);
        }
        return note;
    }

    @RequestMapping(value = "getLastNotes",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Note> getLastNotes(){
        List<Note> lastNotesList = new ArrayList<>();
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            lastNotesList = notesService.getLastUserNotes(new User(SecurityContextHolder.getContext().getAuthentication().getName(), null));
        }
        return lastNotesList;
    }

    @RequestMapping(value = "getAllNotes",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Note> getAllNotes(){
        List<Note> allNotesList = new ArrayList<>();
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            allNotesList = notesService.getUserNotes(new User(SecurityContextHolder.getContext().getAuthentication().getName(), null));
        }
        return allNotesList;
    }

    @RequestMapping(value = "deleteNote",method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String deleteNote(@RequestBody Note tmpNote){
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            Note note = notesService.getNoteWithUser(tmpNote.getId());
            String username = note.getUser().getUsername();
            if(note!=null || username.equals(SecurityContextHolder.getContext().getAuthentication().getName())){
                notesService.delete(note);
                return "Success";
            }
        }
        return "Fail";
    }

    @RequestMapping(value = "editNote",method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public void editNote(@RequestBody Note note){
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            notesService.update(note);
        }
    }

}
