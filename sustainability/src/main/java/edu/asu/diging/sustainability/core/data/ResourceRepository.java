package edu.asu.diging.sustainability.core.data;

import org.springframework.data.repository.PagingAndSortingRepository;

import edu.asu.diging.sustainability.core.model.impl.Resource;

public interface ResourceRepository extends PagingAndSortingRepository<Resource, String> {

}
