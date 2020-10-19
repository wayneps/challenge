package com.enrollment.enrolleetracker.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author waynes
 *
 *       Purpose:  Repository for Enrollee CRUD database operations.
 */
@RepositoryRestResource(exported = false)
public interface EnrolleeRepository extends CrudRepository<Enrollee, Long> {

}
