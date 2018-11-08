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

/**
 * @author Namratha 
 * Used to configure accessibility to Concepts for different roles.
 */
@Controller
@PropertySource("classpath:config.properties")
public class ConceptConfigurationController {

    @Autowired
    private IConceptManager conceptManager;

    /**
     * 
     * To display current configuration.
     */
    @RequestMapping(value = "/admin/concept/configuration", method = RequestMethod.GET)
    public String showConfiguration(Model model) {
        List<IConcept> childConcepts = new ArrayList<IConcept>();
        for (IConcept concept : conceptManager.getTopConcepts()) {
            childConcepts.addAll(concept.getChildren());
        }
        model.addAttribute("concepts", childConcepts);
        model.addAttribute("roles", Roles.values());
        model.addAttribute("form", new ConceptConfigurationForm());
        return "admin/concept/configuration";
    }

    /**
     * To store the updated configuration.
     */
    @RequestMapping(value = "/admin/concept/configuration", method = RequestMethod.POST)
    public String updateConfiguration(
            @ModelAttribute("form") @Validated ConceptConfigurationForm conceptConfigurationForm,
            BindingResult results, Model model, HttpServletRequest request) {
        List<ConceptForm> conceptList = conceptConfigurationForm.getConcepts();
        conceptList.size();
        for (ConceptForm concept : conceptList) {
            conceptManager.configureConceptRoles(concept.getId(), concept.getRoles());
        }
        return "admin/concept/list";
    }
}
