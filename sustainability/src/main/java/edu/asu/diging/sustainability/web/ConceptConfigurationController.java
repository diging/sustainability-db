package edu.asu.diging.sustainability.web;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import edu.asu.diging.sustainability.core.model.IConcept;
import edu.asu.diging.sustainability.core.model.impl.Roles;
import edu.asu.diging.sustainability.core.service.IConceptManager;

@Controller
@PropertySource("classpath:config.properties")
public class ConceptConfigurationController {

    @Autowired
    private IConceptManager conceptManager;

    @RequestMapping(value = "/admin/concept/configuration", method=RequestMethod.GET)
    public String showConfiguration(Model model) {
        List<IConcept> allConcepts = new ArrayList<IConcept>();
        for(IConcept con: conceptManager.getTopConcepts())
            allConcepts.addAll(con.getChildren());
        model.addAttribute("concepts", allConcepts);
        model.addAttribute("roles",Roles.values());
        model.addAttribute("form", new ConceptConfigurationForm());
        return "admin/concept/configuration";
    }

    @RequestMapping(value = "/admin/concept/configuration", method=RequestMethod.POST)
    public String updateConfiguration(@Validated @ModelAttribute("form") ConceptConfigurationForm conceptConfigurationForm, HttpServletRequest request, Model model, BindingResult results) {
        List<ConceptForm> conc = conceptConfigurationForm.getConcepts();
        conc.size();
        for(ConceptForm con: conc)
        {
            System.out.println(con.getRoles() + con.getId());
            conceptManager.updateConceptRoles(con.getId(), con.getRoles());
        }
        return "admin/concept/list";
    }
}
