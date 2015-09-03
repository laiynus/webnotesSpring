package by.khrapovitsky.controller;

import by.khrapovitsky.model.Note;
import by.khrapovitsky.model.User;
import by.khrapovitsky.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping(value = {"/notes"})
public class NotesController {

    @Autowired
    private NotesService notesService;
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView defaultPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("notes");
        return model;
    }

    @Secured("isAuthenticated()")
    @RequestMapping(value = "addNote", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String addNote(@RequestBody Note note) throws IOException {
        if(note.getNote() == null || note.getNote().isEmpty()){
            return "Note can't be empty!";
        }else{
            if(note.getNote().length()>1000){
                return "The maximum note length is 1000 characters!";
            }else{
                note.setUser(new User(SecurityContextHolder.getContext().getAuthentication().getName(), null));
                note.setDateTimeCreate(new Timestamp(new java.util.Date().getTime()));
                notesService.insert(note);
                return "Success";
            }
        }
    }

    @Secured("isAuthenticated()")
    @RequestMapping(value = "getLastNotes",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Note> getLastNotes(){
        List<Note> lastNotesList;
        lastNotesList = notesService.getLastUserNotes(new User(SecurityContextHolder.getContext().getAuthentication().getName(), null));
        return lastNotesList;
    }

    @Secured("isAuthenticated()")
    @RequestMapping(value = "getAllNotes",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Note> getAllNotes(){
        List<Note> allNotesList;
        allNotesList = notesService.getUserNotes(new User(SecurityContextHolder.getContext().getAuthentication().getName(), null));
        return allNotesList;
    }

    @Secured("isAuthenticated()")
    @RequestMapping(value = "deleteNote",method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String deleteNote(@RequestBody Note tmpNote){
        Note note = notesService.getNoteWithUser(tmpNote.getId());
        if (note != null) {
            if (note.getUser().getUsername().equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
                notesService.delete(note);
                return "Success";
            }else{
                return "Access denied!";
            }
        }
        return "This note is not found!";
    }

    @Secured("isAuthenticated()")
    @RequestMapping(value = "editNote",method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String editNote(@RequestBody Note note){
        if(note.getNote() == null || note.getNote().isEmpty()){
            return "Note can't be empty!";
        }else {
            if(note.getNote().length()>1000){
                return "The maximum note length is 1000 characters!";
            }else {
                Note tmpNote = notesService.getNoteWithUser(note.getId());
                if (tmpNote != null) {
                    if (tmpNote.getUser().getUsername().equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
                        tmpNote.setDateTimeCreate(new Timestamp(new java.util.Date().getTime()));
                        tmpNote.setNote(note.getNote());
                        notesService.update(tmpNote);
                        return "Success";
                    } else {
                        return "Access denied!";
                    }
                }
            }
        }
        return "This note is not found!";
    }

    @Secured("isAuthenticated()")
    @RequestMapping(value = "getNote",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Note getNote(@RequestBody Note note){
        if (notesService.getNote(note.getId()) != null) {
            if (notesService.getNoteWithUser(note.getId()).getUser().getUsername().equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
                Note requestNote = notesService.getNote(note.getId());
                return requestNote;
            }
        }
        return null;
    }

}
