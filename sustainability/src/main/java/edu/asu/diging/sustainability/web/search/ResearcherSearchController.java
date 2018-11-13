package edu.asu.diging.sustainability.web.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.asu.diging.sustainability.core.data.ConceptRepository;
import edu.asu.diging.sustainability.core.exception.NotAValidResourceException;
import edu.asu.diging.sustainability.core.model.IAnnotation;
import edu.asu.diging.sustainability.core.model.IConcept;
import edu.asu.diging.sustainability.core.model.IResource;
import edu.asu.diging.sustainability.core.model.impl.Concept;
import edu.asu.diging.sustainability.core.service.IAnnotationManager;
import edu.asu.diging.sustainability.core.service.IResourceManager;

@Controller
public class ResearcherSearchController {
    
    @Autowired
    private IAnnotationManager annotationManager;
    
    @Autowired
    private ConceptRepository conceptRepo;
    
    @Autowired
    private IResourceManager resourceManager;
    
    @RequestMapping(value="/perspective/search", method=RequestMethod.POST)
    public String search(Model model, @RequestParam("selectedConcepts") String[] selectedConcepts) {
    	Map<String, List<IAnnotation>> URIList = annotationManager.findTextsForConcepts(selectedConcepts);
        List<IResource> resourceList = new ArrayList<IResource>();
        for(String s: URIList.keySet()) {
            try {
                resourceList.add(resourceManager.getResource(s));
            } catch (NotAValidResourceException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        model.addAttribute("resource",resourceList);
        //model.addAttribute("results", annotationManager.findTextsForConcepts(selectedConcepts));
        
        List<IConcept> concepts = new ArrayList<>();
        for (String conceptId : selectedConcepts) {
            Optional<Concept> concept = conceptRepo.findById(conceptId);
            if (concept.isPresent()) {
                concepts.add((IConcept) concept.get());
            }
        }
        model.addAttribute("concepts", concepts);
        
        return "perspective/researcher/search";
    }
    
    @RequestMapping(value="/perspective/researcher/resource", method=RequestMethod.GET)
    public IResource reload(Model model, @RequestParam("uri") String uri) {
    		System.out.println("uri "+uri);
    		try {
                return resourceManager.getResource(uri);
            } catch (NotAValidResourceException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    		return null;
    }
}
