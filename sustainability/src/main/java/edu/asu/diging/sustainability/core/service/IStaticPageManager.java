package edu.asu.diging.sustainability.core.service;

import edu.asu.diging.sustainability.core.model.IStaticPage;
import edu.asu.diging.sustainability.core.model.PageType;

public interface IStaticPageManager {

    IStaticPage storeStaticPage(String title, String content, PageType pageType);

    IStaticPage getStaticPage(PageType pageType);

}