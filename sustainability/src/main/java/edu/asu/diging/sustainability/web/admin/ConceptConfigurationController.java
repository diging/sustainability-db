package edu.asu.diging.sustainability.web.admin;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import edu.asu.diging.sustainability.core.model.SearchCategory;
import edu.asu.diging.sustainability.core.service.IConceptManager;
import edu.asu.diging.sustainability.web.admin.pages.ConceptForm;

/**
 * @author namrathaov
 * 
 *         Controller for configuring the search categories of concepts.
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
        model.addAttribute("concepts", conceptManager.getTopConcepts());
        model.addAttribute("categories", SearchCategory.values());
        model.addAttribute("form", new ConceptConfigurationForm());
        return "admin/concept/configuration";
    }

    /**
     * To store the updated configuration.
     */
    @RequestMapping(value = "/admin/concept/configuration", method = RequestMethod.POST)
    public String updateConfiguration(
            @ModelAttribute("form") ConceptConfigurationForm conceptConfigurationForm,
            BindingResult results, Model model, HttpServletRequest request) {
        for (ConceptForm concept : conceptConfigurationForm.getConcepts()) {
            conceptManager.storeConceptSearchCategoriesAndAlias(concept.getId(),
                    concept.getSearchCategories(), concept.getConceptAlias());
        }
        return "redirect:/admin/concept/list";
    }
}
