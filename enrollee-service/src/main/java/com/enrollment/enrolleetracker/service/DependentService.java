package com.enrollment.enrolleetracker.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enrollment.enrolleetracker.jpa.Dependent;
import com.enrollment.enrolleetracker.jpa.DependentRepository;
import com.enrollment.enrolleetracker.jpa.Enrollee;

/**
 * @author waynes
 *
 *       Purpose:  Provides Dependent service operations.
 */
@Service
public class DependentService {

	@Autowired
	private DependentRepository dependentRepository;
	
	public Iterable<Dependent> findAll() {
		return dependentRepository.findAll();
	}
	
	public Optional<Dependent> findById(UUID id) {
		return dependentRepository.findById(id);
	}
	
	public <S extends Dependent> S save(S enrollee) {
		return dependentRepository.save(enrollee);
	}	
	
	public void delete(UUID id) {
		dependentRepository.deleteById(id);
	}	
	
	public List<Dependent> findByEnrollee(Enrollee enrollee) {
		return dependentRepository.findByEnrollee(enrollee);
	}	
	
}
