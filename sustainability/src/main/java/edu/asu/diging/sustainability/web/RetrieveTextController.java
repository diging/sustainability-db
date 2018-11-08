package edu.asu.diging.sustainability.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.asu.diging.sustainability.core.model.IAnnotation;
import edu.asu.diging.sustainability.core.model.IConcept;
import edu.asu.diging.sustainability.core.model.config.IAnnotationConfiguration;
import edu.asu.diging.sustainability.core.service.IAnnotationConfigurationManager;
import edu.asu.diging.sustainability.core.service.IAnnotationManager;
import edu.asu.diging.sustainability.core.service.IResourceManager;

@Controller
public class RetrieveTextController {
    
    @Autowired
    private IResourceManager resourceManager;
    
    @Autowired
    private IAnnotationManager annotationManager;
    
    @Autowired
    private IAnnotationConfigurationManager configManager;

    @RequestMapping(value="/text")
    public String get(Model model, @RequestParam("uri") String uri) {
        model.addAttribute("resource", resourceManager.getResource(uri));
        Map<IConcept, List<IAnnotation>> annotations = annotationManager.getAnnotationsForTextByConceptHierachy(uri);
        Map<IConcept, Map<IConcept, List<IAnnotation>>> annotationsByParentConcepts = new HashMap<>();
        annotations.forEach((k, v) -> {
            if (!annotationsByParentConcepts.containsKey(k)) {
                annotationsByParentConcepts.put(k, new HashMap<>());
            }
            v.forEach(a -> {
                Map<IConcept, List<IAnnotation>> annotationsByConcept = annotationsByParentConcepts.get(k);
                if (!annotationsByConcept.containsKey(a.getConcept())) {
                    annotationsByConcept.put(a.getConcept(), new ArrayList<>());
                }
                annotationsByConcept.get(a.getConcept()).add(a);
            });
           
        });
        model.addAttribute("annotations", annotationsByParentConcepts);
        
        List<IAnnotationConfiguration> configs = configManager.findAll();
        Map<String, IAnnotationConfiguration> configMap = new HashMap<>();
        configs.forEach(c -> configMap.put(c.getConcept().getId(), c));
        model.addAttribute("configs", configMap);
        return "text";
        
    }
}
