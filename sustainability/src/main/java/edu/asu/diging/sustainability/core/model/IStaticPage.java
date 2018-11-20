package edu.asu.diging.sustainability.core.model;

public interface IStaticPage {

    String getId();

    void setId(String id);

    String getTitle();

    void setTitle(String title);

    String getContent();

    void setContent(String content);

    PageType getPageType();

    void setPageType(PageType pageType);

}