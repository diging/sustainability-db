package edu.asu.diging.sustainability.core.model.impl;

import java.util.List;
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
    private String alias;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

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
