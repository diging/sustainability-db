package edu.asu.diging.sustainability.core.data;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import edu.asu.diging.sustainability.core.model.PageType;
import edu.asu.diging.sustainability.core.model.impl.StaticPage;

public interface StaticPageRepository extends PagingAndSortingRepository<StaticPage, String> {

    public List<StaticPage> findByPageType(PageType type);
    
}
