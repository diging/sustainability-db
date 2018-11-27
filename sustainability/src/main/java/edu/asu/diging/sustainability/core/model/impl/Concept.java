package edu.asu.diging.sustainability.core.model.impl;

import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.context.annotation.PropertySource;
import edu.asu.diging.sustainability.core.model.IConcept;
import edu.asu.diging.sustainability.core.model.SearchCategory;

@Entity
@PropertySource("classpath:config.properties")
public class Concept implements IConcept {

    @Id
    @GeneratedValue(generator = "concept-id-generator")
    @GenericGenerator(name = "concept-id-generator",
            parameters = @Parameter(name = "prefix", value = "CON"),
            strategy = "edu.asu.diging.sustainability.core.data.IdGenerator")
    private String id;
    private String name;
    private String uri;

    @ManyToOne(targetEntity = Concept.class)
    private IConcept parent;

    @OneToMany(targetEntity = Concept.class)
    private List<IConcept> children;

    @ElementCollection(targetClass = SearchCategory.class)
    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "concept_id")
    private List<SearchCategory> searchCategories;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getUri() {
        return uri;
    }

    @Override
    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public IConcept getParent() {
        return parent;
    }

    @Override
    public void setParent(IConcept parent) {
        this.parent = parent;
    }

    @Override
    public List<IConcept> getChildren() {
        
        //Pattern for left to right integer scanning
        Pattern alphaNumPattern = Pattern.compile("\\d+");
        
        //Custom alphanumeric comparator for natural order sorting 
        Comparator<IConcept> c = new Comparator<IConcept>() {
            @Override
            public int compare(IConcept concept1, IConcept concept2) {
                Matcher match = alphaNumPattern.matcher(concept1.getName());
                if (!match.find()) {
                    return (concept1.getName()).compareTo(concept2.getName());
                }
                else {
                    Integer number1, number2 = null;
                    number1 = Integer.parseInt(match.group());
                    match = alphaNumPattern.matcher(concept2.getName());
                    if (!match.find()) {
                        return concept1.getName().compareTo(concept2.getName());
                    }
                    else {
                        number2 = Integer.parseInt(match.group());
                        int comparison = number1.compareTo(number2);
                        if (comparison != 0) {
                            return comparison;
                        }
                        else {
                            return concept1.getName().compareTo(concept2.getName());
                        }
                    }
                }
            }
        };
        children.sort(c);
        return children;
    }

    @Override
    public void setChildren(List<IConcept> children) {
        this.children = children;
    }



    public List<SearchCategory> getSearchCategories() {
        return searchCategories;
    }

    public void setSearchCategories(List<SearchCategory> searchCategories) {
        this.searchCategories = searchCategories;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((uri == null) ? 0 : uri.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Concept other = (Concept) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (uri == null) {
            if (other.uri != null)
                return false;
        } else if (!uri.equals(other.uri))
            return false;
        return true;
    }

}
