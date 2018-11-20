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
import edu.asu.diging.sustainability.web.util.MarkdownUtil;

@Controller
public class GlossaryController {

    @Autowired
    private IStaticPageManager staticPageManager;

    @Autowired
    private MarkdownUtil markdownUtil;

    @RequestMapping("glossary")
    public String contact(Model model) {
        IStaticPage page = staticPageManager.getStaticPage(PageType.GLOSSARY);
        model.addAttribute("title", page.getTitle() != null ? page.getTitle() : "");
        model.addAttribute("content", markdownUtil.render(page.getContent() != null ? page.getContent() : ""));
        
        return "glossary";
    }
}
