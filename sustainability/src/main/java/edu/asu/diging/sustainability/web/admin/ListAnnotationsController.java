package edu.asu.diging.sustainability.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.asu.diging.sustainability.core.service.IAnnotationManager;

@Controller
public class ListAnnotationsController {
    
    @Autowired
    private IAnnotationManager annotationManager;

    @RequestMapping(value="/admin/annotation/list")
    public String list(Model model) {
        model.addAttribute("annotations", annotationManager.listAnnotations());
        return "admin/annotation/list";
    }
}
