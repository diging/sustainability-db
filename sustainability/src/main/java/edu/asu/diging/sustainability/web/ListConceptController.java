package edu.asu.diging.sustainability.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import edu.asu.diging.sustainability.core.model.IConcept;
import edu.asu.diging.sustainability.core.service.IConceptManager;

@Controller
public class ListConceptController {

    @Autowired
    private IConceptManager conceptManager;

    @RequestMapping(value = "/admin/concept/list")
    public String list(Model model) {
        return "admin/concept/list";
    }

    @RequestMapping(value = "/admin/concept/list.json")
    public ResponseEntity<String> listJson() {

        ObjectMapper mapper = new ObjectMapper();
        ArrayNode concepts = mapper.createArrayNode();
        for (IConcept concept : conceptManager.getTopConcepts()) {
            addNode(concept, concepts, mapper);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");

        return ResponseEntity.ok().headers(headers).body(concepts.toString());
    }

    private void addNode(IConcept concept, ArrayNode conceptArray, ObjectMapper mapper) {
        ObjectNode conceptNode = mapper.createObjectNode();
        conceptNode.put("text", concept.getName());
        conceptArray.add(conceptNode);

        if (concept.getChildren() != null) {
            ArrayNode children = mapper.createArrayNode();
            conceptNode.put("nodes", children);
            for (IConcept child : concept.getChildren()) {
                addNode(child, children, mapper);
            }

        }
    }

    @RequestMapping(value = "/admin/concept/configuration")
    public String configuration(Model model) {
        List<IConcept> concepts = conceptManager.getTopConcepts();
        model.addAttribute("concepts", concepts);
        List<String> roles = new ArrayList<String>();
        roles.add("Researcher");
        roles.add("Practitioner");
        model.addAttribute("roles", roles);
        return "admin/concept/configuration";
    }

}
