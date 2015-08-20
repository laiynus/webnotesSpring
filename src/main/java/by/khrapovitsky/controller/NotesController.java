package by.khrapovitsky.controller;

import by.khrapovitsky.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
}
