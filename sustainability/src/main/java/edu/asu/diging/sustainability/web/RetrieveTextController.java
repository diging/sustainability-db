package edu.asu.diging.sustainability.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.asu.diging.sustainability.core.model.IResource;
import edu.asu.diging.sustainability.core.service.IResourceManager;

@Controller
public class RetrieveTextController {
    
    @Autowired
    private IResourceManager resourceManager;

    @RequestMapping(value="/text")
    public String get(Model model, @RequestParam("uri") String uri) {
        model.addAttribute("resource", resourceManager.getResource(uri));
        return "text";
        
    }
}
