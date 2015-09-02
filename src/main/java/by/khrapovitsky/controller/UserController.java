package by.khrapovitsky.controller;

import by.khrapovitsky.model.User;
import by.khrapovitsky.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    UsersService usersService;

    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authenticationManager;

    @RequestMapping(value = {"/sign**", "/"}, method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }
        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("sign");
        if (!(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
            return new ModelAndView("redirect:/notes");
        }
        return model;
    }

    @RequestMapping(value = {"/registration"}, method = RequestMethod.GET)
    public ModelAndView registration() {
        ModelAndView model = new ModelAndView();
        model.setViewName("registration");
        if (!(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
            return new ModelAndView("redirect:/notes");
        }
        User newUser = new User();
        model.addObject("user", newUser);
        return model;
    }

    @RequestMapping(value = {"registrationUser"}, method = RequestMethod.POST)
    public ModelAndView registerUser(@ModelAttribute("user") User user) {
        ModelAndView model = new ModelAndView();
        if(usersService.getUser(user.getUsername())!=null){
            model.addObject("error","This user already exist!");
            model.setViewName("registration");
            return model;
        }else{
            usersService.insert(user);
        }
        try {
            UserDetails userDetails = usersService.getUser(user.getUsername());
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, user.getPassword(), userDetails.getAuthorities());
            authenticationManager.authenticate(auth);
            if(auth.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(auth);
                model.setViewName("redirect:/notes");
                return model;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.setViewName("redirect:/sign");
        return model;
    }

}
