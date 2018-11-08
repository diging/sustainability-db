package edu.asu.diging.sustainability.web.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.asu.diging.sustainability.core.model.IConcept;
import edu.asu.diging.sustainability.core.model.config.DisplayType;
import edu.asu.diging.sustainability.core.model.config.IAnnotationConfiguration;
import edu.asu.diging.sustainability.core.model.config.Section;
import edu.asu.diging.sustainability.core.model.config.impl.AnnotationConfiguration;
import edu.asu.diging.sustainability.core.service.IAnnotationConfigurationManager;
import edu.asu.diging.sustainability.core.service.IConceptManager;
import edu.asu.diging.sustainability.web.admin.pages.AnnotationConfig;
import edu.asu.diging.sustainability.web.admin.pages.AnnotationConfigForm;

@Controller
public class AnnotationConfigController {

    @Autowired
    private IAnnotationConfigurationManager manager;
    
    @Autowired
    private IConceptManager conceptManager;
    
    @RequestMapping(value="/admin/text/config")
    public String show(Model model) {
        List<IConcept> concepts = conceptManager.getTopConcepts();
        model.addAttribute("concepts", concepts);
        
        AnnotationConfigForm form = new AnnotationConfigForm();
        List<IAnnotationConfiguration> configsObjects = manager.findAll();
        List<AnnotationConfig> configs = new ArrayList<>();
        Map<String, AnnotationConfig> configsByConcept = new HashMap<>();
        configsObjects.forEach(c -> {
            AnnotationConfig config = new AnnotationConfig();
            config.setConceptId(c.getConcept().getId());
            config.setDisplayType(c.getDisplayType());
            config.setId(c.getId());
            config.setSection(c.getSection());
            config.setSortOrder(c.getSortOrder());
            config.setConceptName(c.getConcept().getName());
            configsByConcept.put(c.getConcept().getId(), config);
        });
        
        concepts.forEach(c -> {
            if (!configsByConcept.containsKey(c.getId())) {
                AnnotationConfig config = new AnnotationConfig();
                config.setConceptId(c.getId());
                config.setConceptName(c.getName());
                configsByConcept.put(c.getId(), config);
            }
        });
        
        form.setAnnotationConfigs(new ArrayList<>(configsByConcept.values()));
        model.addAttribute("form", form);
        
        Map<String, String> sectionMap = new HashMap<>();
        for(Section section: Section.values()) {
            String name = section.name();
            String humanized = name.toLowerCase();
            humanized = humanized.substring(0, 1).toUpperCase() + humanized.substring(1);
            
            sectionMap.put(name, humanized);
        }
        model.addAttribute("sections", sectionMap);
        
        Map<String, String> displayMap = new HashMap<>();
        for(DisplayType type: DisplayType.values()) {
            String name = type.name();
            String humanized = name.toLowerCase();
            humanized = humanized.substring(0, 1).toUpperCase() + humanized.substring(1);
            humanized = humanized.replace("_", " ");
            
            displayMap.put(name, humanized);
        }
        model.addAttribute("displayTypes", displayMap);
        
        return "admin/text/config";
    }
    
    @RequestMapping(value="/admin/text/config", method=RequestMethod.POST)
    public String store(@ModelAttribute("form") AnnotationConfigForm form, RedirectAttributes redirectAttrs) {
        manager.storeConfigurations(form.getAnnotationConfigs());
        redirectAttrs.addFlashAttribute("show_alert", true);
        redirectAttrs.addFlashAttribute("alert_type", "success");
        redirectAttrs.addFlashAttribute("alert_msg", "Text configuration was successfully saved.");
        return "redirect:/admin/text/config";
    }
}
