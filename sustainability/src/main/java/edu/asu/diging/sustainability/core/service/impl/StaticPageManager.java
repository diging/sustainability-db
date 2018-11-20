package edu.asu.diging.sustainability.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.asu.diging.sustainability.core.data.StaticPageRepository;
import edu.asu.diging.sustainability.core.model.IStaticPage;
import edu.asu.diging.sustainability.core.model.PageType;
import edu.asu.diging.sustainability.core.model.impl.StaticPage;
import edu.asu.diging.sustainability.core.service.IStaticPageManager;

@Service
public class StaticPageManager implements IStaticPageManager {
    
    @Autowired
    private StaticPageRepository pageRepo;

    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.service.impl.IStaticPageManager#storeStaticPage(java.lang.String, java.lang.String, edu.asu.diging.sustainability.core.model.PageType)
     */
    @Override
    public IStaticPage storeStaticPage(String title, String content, PageType pageType) {
        IStaticPage page = getStaticPage(pageType);
        if (page == null) {
            page = new StaticPage();
            page.setPageType(pageType);
        }
        
        page.setContent(content);
        page.setTitle(title);
        
        return (IStaticPage) pageRepo.save((StaticPage)page);
    }
    
    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.service.impl.IStaticPageManager#getStaticPage(edu.asu.diging.sustainability.core.model.PageType)
     */
    @Override
    public IStaticPage getStaticPage(PageType pageType) {
        List<StaticPage> pages = pageRepo.findByPageType(pageType);
        // there should only be one of each type
        if (pages == null || pages.isEmpty()) {
            return null;
        }
        
        return pages.get(0);
    }
}
