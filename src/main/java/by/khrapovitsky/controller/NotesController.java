package by.khrapovitsky.controller;

import by.khrapovitsky.model.Note;
import by.khrapovitsky.model.User;
import by.khrapovitsky.service.NotesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.sql.Timestamp;

@Controller
public class NotesController {

    @Autowired
    private NotesService notesService;

    @RequestMapping(value = {"/lastnotes"}, method = RequestMethod.GET)
    public ModelAndView defaultPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("lastnotes");
        return model;
    }

    @RequestMapping(value = "/addNote.web",  method = RequestMethod.POST)
    @ResponseBody
    public void addNote(@RequestBody String noteText) throws Exception {
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            Note note = new Note((new User(SecurityContextHolder.getContext().getAuthentication().getName(), null)),noteText,new Timestamp(new java.util.Date().getTime()));
            notesService.insert(note);
        }
        else{
            throw new Exception("Your are not login!");
        }
    }

}
