package edu.asu.diging.sustainability.web.search;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.asu.diging.sustainability.core.model.IConcept;
import edu.asu.diging.sustainability.core.model.impl.SearchCategory;
import edu.asu.diging.sustainability.core.service.IConceptManager;

@Controller
public class PractitionerPerspectiveController {

    @Autowired
    private IConceptManager conceptManager;

    @RequestMapping("/perspective/practitioner")
    public String show(Model model) {
        
        List<IConcept> concepts = conceptManager.getTopConcepts().stream().filter(c -> c.getSearchCategories().contains(SearchCategory.PRACTITIONER)).collect(Collectors.toList());
        model.addAttribute("concepts", concepts);
        
        return "perspective/concepts";
    }
}
