package by.khrapovitsky.controller;

import by.khrapovitsky.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class NotesController {

    @Autowired
    private NotesService notesService;


}
