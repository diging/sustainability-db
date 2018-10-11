package edu.asu.diging.sustainability.web;

import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import edu.asu.diging.sustainability.core.service.IAnnotationManager;

@Controller
public class AddAnnotationsController {
    
    @Autowired
    private IAnnotationManager annotationManager;
    
    @RequestMapping(value = "/admin/annotation/add", method = RequestMethod.GET)
    public String getAddAnnotations() {
        return "admin/annotation/add";
    }
    
    @RequestMapping(value = "/admin/annotation/add", method = RequestMethod.POST)
    public String postAddAnnotations(@RequestParam("file") MultipartFile file, Principal principal) throws IOException {

        byte[] annotationsFile = null;
        String filename = null;
        if (file  != null) {
            annotationsFile = file.getBytes();
            filename = file.getOriginalFilename();
        }
        annotationManager.addAnnotations(annotationsFile, filename);
        
        return "redirect:/admin/annotation/add";
    }

}
