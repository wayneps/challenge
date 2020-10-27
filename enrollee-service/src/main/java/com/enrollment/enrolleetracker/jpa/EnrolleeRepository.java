package com.enrollment.enrolleetracker.jpa;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author waynes
 *
 *       Purpose:  Repository for Enrollee CRUD database operations.
 */
@RepositoryRestResource(exported = false)
//public interface EnrolleeRepository extends CrudRepository<Enrollee, Long> {
public interface EnrolleeRepository extends PagingAndSortingRepository<Enrollee, Long> {
	
	Iterable<Enrollee> findByNameContaining(String name);
}
