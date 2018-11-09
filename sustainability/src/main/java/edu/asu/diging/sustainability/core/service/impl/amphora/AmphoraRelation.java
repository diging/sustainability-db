package edu.asu.diging.sustainability.core.service.impl.amphora;

public class AmphoraRelation {

    private long id;
    private String uri;
    private String url;
    private String name;
    
    private RelationTarget target;
    private RelationPredicate predicate;
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getUri() {
        return uri;
    }
    public void setUri(String uri) {
        this.uri = uri;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public RelationTarget getTarget() {
        return target;
    }
    public void setTarget(RelationTarget target) {
        this.target = target;
    }
    public RelationPredicate getPredicate() {
        return predicate;
    }
    public void setPredicate(RelationPredicate predicate) {
        this.predicate = predicate;
    }
    
}
