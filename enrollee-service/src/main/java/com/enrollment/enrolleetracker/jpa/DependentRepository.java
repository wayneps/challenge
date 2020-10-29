package com.enrollment.enrolleetracker.jpa;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author waynes
 *
 *       Purpose:  Repository for Dependent CRUD database operations.
 */
@RepositoryRestResource(exported = false)
public interface DependentRepository extends CrudRepository<Dependent, UUID> {

	List<Dependent> findByEnrollee(Enrollee enrollee);
}
