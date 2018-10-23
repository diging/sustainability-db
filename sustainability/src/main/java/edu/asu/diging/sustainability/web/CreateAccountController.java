package edu.asu.diging.sustainability.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.asu.diging.sustainability.web.pages.UserForm;

@Controller
public class CreateAccountController {

    @RequestMapping(value = "/register", method=RequestMethod.GET)
    public String get(Model model) {
        model.addAttribute("user", new UserForm());
        return "register";
    }
    
    @RequestMapping(value = "/register", method=RequestMethod.POST)
    public String post(@ModelAttribute("user") UserForm user) {
        
        return "redirect:/ ";
    }
}
