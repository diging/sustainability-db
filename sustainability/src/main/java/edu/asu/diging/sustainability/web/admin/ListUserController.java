package edu.asu.diging.sustainability.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.asu.diging.sustainability.core.service.IUserManager;

@Controller
public class ListUserController {

    @Autowired
    private IUserManager userManager;

    @RequestMapping("/admin/user/list")
    public String list(Model model) {
        model.addAttribute("users", userManager.findAll());
        return "admin/user/list";
    }
}
