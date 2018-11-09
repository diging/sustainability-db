package edu.asu.diging.sustainability.core.service.impl.amphora;

import java.util.List;

public class AmphoraResponse {

    private String url;
    private long id;
    private String uri;
    private String name;
    private List<AmphoraRelation> relations_from;
    
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
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
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<AmphoraRelation> getRelations_from() {
        return relations_from;
    }
    public void setRelations_from(List<AmphoraRelation> relations_from) {
        this.relations_from = relations_from;
    }
    
}
