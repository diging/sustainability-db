package edu.asu.diging.sustainability.web;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.asu.diging.sustainability.core.model.IStaticPage;
import edu.asu.diging.sustainability.core.model.PageType;
import edu.asu.diging.sustainability.core.service.IStaticPageManager;

@Controller
public class AboutController {

    @Autowired
    private IStaticPageManager staticPageManager;

    @RequestMapping("about")
    public String contact(Model model) {
        IStaticPage page = staticPageManager.getStaticPage(PageType.ABOUT);
        model.addAttribute("title", page.getTitle());
        
        Parser parser = Parser.builder().build();
        Node document = parser.parse(page.getContent());
        HtmlRenderer renderer = HtmlRenderer.builder().softbreak("<br>").build();
        model.addAttribute("content", renderer.render(document));
        
        return "about";
    }
}
