package edu.asu.diging.sustainability.web.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.asu.diging.sustainability.core.exception.NotAValidResourceException;
import edu.asu.diging.sustainability.core.service.IResourceManager;

@Controller
public class UpdateTextController {
    
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IResourceManager resourceManager;
    
    @RequestMapping(value="/admin/text/update", method=RequestMethod.POST)
    public String update(@RequestParam String uri, RedirectAttributes redirectAttrs) {
        try {
            resourceManager.updateResource(uri);
            redirectAttrs.addFlashAttribute("show_alert", true);
            redirectAttrs.addFlashAttribute("alert_type", "success");
            redirectAttrs.addFlashAttribute("alert_msg", "The text " + uri + " is being updated. Please refresh the page to see updated information.");
        } catch (NotAValidResourceException e) {
            logger.warn("Resource with URI " + uri + " could not be udpated.", e);
            redirectAttrs.addFlashAttribute("show_alert", true);
            redirectAttrs.addFlashAttribute("alert_type", "danger");
            redirectAttrs.addFlashAttribute("alert_msg", "The text " + uri + " could not be updated. Its URI has an incorrect format.");
        }     
        
        return "redirect:/admin/text/list";
    }
}
