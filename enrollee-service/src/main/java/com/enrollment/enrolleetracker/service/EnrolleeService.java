package com.enrollment.enrolleetracker.service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.enrollment.enrolleetracker.jpa.Dependent;
import com.enrollment.enrolleetracker.jpa.Enrollee;
import com.enrollment.enrolleetracker.jpa.EnrolleeRepository;

/**
 * @author waynes
 *
 *       Purpose:  Provides Enrollee service operations.
 */
@Service
public class EnrolleeService {
	
	@Autowired
	private EnrolleeRepository  enrolleeRepository;
	
	@Autowired
	private DependentService dependentService;
	
	
	public Iterable<Enrollee> findAll() {
		return enrolleeRepository.findAll();
	}
	
	
	public Iterable<Enrollee> findAllPaginated(Integer page, Integer size) {

        Pageable paging = PageRequest.of(page, size);
        Page<Enrollee> pagedResult = enrolleeRepository.findAll(paging);
		return pagedResult.toList();
	}
	
	public Iterable<Enrollee> findByNameContaining(String name) {
		
		return enrolleeRepository. findByNameContaining(name);

	}	
	
	public Optional<Enrollee> findById(Long id) {
		return enrolleeRepository.findById(id);
	}
	
	public <S extends Enrollee> S save(S enrollee) {
		return enrolleeRepository.save(enrollee);
	}	
	
	public void delete(Long enrolleeId) {
		enrolleeRepository.deleteById(enrolleeId);
	}	

	public <S extends Dependent> S save(Long enrolleeId, S dependent) {
		dependent.setEnrollee(enrolleeRepository.findById(enrolleeId).get());
		return dependentService.save(dependent);
	}
	
	public Iterable<Dependent> findDependentsByEnrolleeId(Long enrolleeId) {
		return dependentService.findByEnrollee(enrolleeRepository.findById(enrolleeId).get());
	}
}
