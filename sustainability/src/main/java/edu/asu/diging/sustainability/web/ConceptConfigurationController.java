package edu.asu.diging.sustainability.web;

import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import edu.asu.diging.sustainability.core.model.IConcept;
import edu.asu.diging.sustainability.core.service.IConceptManager;

@Controller
@PropertySource("classpath:config.properties")
public class ConceptConfigurationController {

    @Autowired
    private IConceptManager conceptManager;

    @Value("#{'${conceptRoles}'.split(',')}")
    private List<String> conceptRoles;


    @RequestMapping(value = "/admin/concept/configuration")
    public String showConfiguration(Model model) {
        model.addAttribute("concepts", conceptManager.getTopConcepts());
        model.addAttribute("roles", conceptRoles);
        return "admin/concept/configuration";
    }

    @RequestMapping(value = "/admin/concept/updateconfiguration")
    public String updateConfiguration(HttpServletRequest request, Model model) {
        List<String> requestParameterNames =
                Collections.list((Enumeration<String>) request.getParameterNames());
        IConcept concept;
        conceptManager.resetRoles();
        for (int i = 0; i < requestParameterNames.size() - 1; i++) {
            if ((concept = conceptManager.getConceptById(requestParameterNames.get(i))) != null) {
                conceptManager.updateConceptRoles(concept, request.getParameterValues(requestParameterNames.get(i)));
            }
        }
        return "admin/concept/list";
    }
}
