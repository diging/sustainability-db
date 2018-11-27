package edu.asu.diging.sustainability.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.asu.diging.sustainability.core.exception.ResourceNotFoundException;
import edu.asu.diging.sustainability.core.model.IStaticPage;
import edu.asu.diging.sustainability.core.model.PageType;
import edu.asu.diging.sustainability.core.service.IStaticPageManager;
import edu.asu.diging.sustainability.web.admin.pages.StaticPageForm;

@Controller
public class StaticPageController {
    
    @Autowired
    private IStaticPageManager pageManager;

    @RequestMapping(value="/admin/pages/{type}/edit", method=RequestMethod.GET)
    public String show(Model model, @PathVariable("type") String type) {
        PageType pageType;
        try {
            pageType = PageType.valueOf(type.toUpperCase());
        } catch(IllegalArgumentException e) {
            throw new ResourceNotFoundException("No page found for " + type);
        }
        
        IStaticPage page = pageManager.getStaticPage(pageType);
        StaticPageForm form = new StaticPageForm();
        if (page != null) {
            form.setTitle(page.getTitle());
            form.setContent(page.getContent());
        }
        model.addAttribute("form", form);
        return "admin/" + type + "/edit";
    }
    
    @RequestMapping(value="/admin/pages/{type}/edit", method=RequestMethod.POST)
    public String store(@ModelAttribute("form") StaticPageForm form, @PathVariable("type") String type) {
        PageType pageType;
        try {
            pageType = PageType.valueOf(type.toUpperCase());
        } catch(IllegalArgumentException e) {
            throw new ResourceNotFoundException("No page found for " + type);
        }
        
        pageManager.storeStaticPage(form.getTitle(), form.getContent(), pageType);
        return "redirect:/admin/pages/" + type + "/edit";
    }
}
