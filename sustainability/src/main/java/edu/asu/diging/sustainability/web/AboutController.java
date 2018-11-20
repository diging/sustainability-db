package edu.asu.diging.sustainability.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.asu.diging.sustainability.core.model.IStaticPage;
import edu.asu.diging.sustainability.core.model.PageType;
import edu.asu.diging.sustainability.core.service.IStaticPageManager;
import edu.asu.diging.sustainability.web.util.MarkdownUtil;

@Controller
public class AboutController {

    @Autowired
    private IStaticPageManager staticPageManager;
    
    @Autowired
    private MarkdownUtil markdownUtil;

    @RequestMapping("about")
    public String contact(Model model) {
        IStaticPage page = staticPageManager.getStaticPage(PageType.ABOUT);
        model.addAttribute("title", page.getTitle());
        model.addAttribute("content", markdownUtil.render(page.getContent()));
        
        return "about";
    }
}
