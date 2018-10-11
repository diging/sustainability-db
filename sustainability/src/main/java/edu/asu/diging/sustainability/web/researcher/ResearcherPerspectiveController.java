package edu.asu.diging.sustainability.web.researcher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.asu.diging.sustainability.core.model.IConcept;
import edu.asu.diging.sustainability.core.service.IConceptManager;

@Controller
public class ResearcherPerspectiveController {
    
    @Autowired
    private IConceptManager conceptManager;

    @RequestMapping("/perspective/researcher")
    public String show(Model model) {
        
        List<IConcept> concepts = conceptManager.getTopConcepts();
        model.addAttribute("concepts", concepts);
        
        return "perspective/researcher";
    }
}
