package edu.asu.diging.sustainability.core.data;

import org.springframework.data.repository.PagingAndSortingRepository;

import edu.asu.diging.sustainability.core.model.impl.User;

public interface UserRepository extends PagingAndSortingRepository<User, String> {

}
