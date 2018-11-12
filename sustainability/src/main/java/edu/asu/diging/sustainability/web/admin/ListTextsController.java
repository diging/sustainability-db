package edu.asu.diging.sustainability.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.asu.diging.sustainability.core.service.IResourceManager;

@Controller
public class ListTextsController {
    
    @Autowired
    private IResourceManager resourceManager;

    @RequestMapping(value="/admin/text/list")
    public String list(Model model) {
        model.addAttribute("texts", resourceManager.findAll());
        return "admin/text/list";
    }
    
    @RequestMapping(value="/admin/text/update", method=RequestMethod.POST)
    public String update(RedirectAttributes redirectAttrs) {
        resourceManager.updateAll();
        redirectAttrs.addFlashAttribute("show_alert", true);
        redirectAttrs.addFlashAttribute("alert_type", "success");
        redirectAttrs.addFlashAttribute("alert_msg", "Texts metadata are being updated. Please refresh page to see progress.");
        
        return "redirect:/admin/text/list";
    }
    
}
