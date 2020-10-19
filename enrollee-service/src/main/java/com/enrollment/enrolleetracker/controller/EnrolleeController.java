package com.enrollment.enrolleetracker.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enrollment.enrolleetracker.jpa.Dependent;
import com.enrollment.enrolleetracker.jpa.Enrollee;
import com.enrollment.enrolleetracker.service.DependentService;
import com.enrollment.enrolleetracker.service.EnrolleeService;

/**
 * @author waynes
 *
 *       Purpose:  Provides Enrollee service operations
 */
@RestController
@RequestMapping("/apiv1/")
public class EnrolleeController {

	@Autowired
	private EnrolleeService enrolleeService;
	
	@Autowired
	private DependentService dependentService;
	
	@GetMapping("/enrollees")
	public Iterable<Enrollee> retreiveEnrollees() {
		return enrolleeService.findAll();
	}
	
	@GetMapping("/enrollees/{enrolleeId}")
	public ResponseEntity<Enrollee> retreiveEnrolleeById(@PathVariable("enrolleeId") Long enrolleeId) {
		
		try {
			return ResponseEntity.ok(enrolleeService.findById(enrolleeId).get());
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}		
	}
	
	
	@PostMapping("/enrollees")
    public Enrollee addEnrollee(@Validated @RequestBody Enrollee enrollee) {
        return enrolleeService.save(enrollee);
    }
	
	
	@DeleteMapping("/enrollees/{enrolleeId}")
	public void removeEnrolleeById(@PathVariable("enrolleeId") Long enrolleeId) {
		enrolleeService.delete(enrolleeId);
	}
		
	@GetMapping("/enrollees/{enrolleeId}/dependents")
	public ResponseEntity<Iterable<Dependent>> getDependents(@PathVariable("enrolleeId") Long enrolleeId) {
		try {
			return ResponseEntity.ok(enrolleeService.findDependentsByEnrolleeId(enrolleeId));
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/dependents")
	public Iterable<Dependent> retreiveDependents() {
		return dependentService.findAll();
	}
	
	@GetMapping("/dependents/{dependentId}")
	public ResponseEntity<Dependent> retreiveDependentById(final @PathVariable("dependentId") Long dependentId) {
		try {
			return ResponseEntity.ok(dependentService.findById(dependentId).get());
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/enrollees/{enrolleeId}/dependent")
    public Dependent addDependent(@PathVariable("enrolleeId") Long enrolleeId, @Validated @RequestBody Dependent dependent) {
        return enrolleeService.save(enrolleeId, dependent);
    }	
	
	@DeleteMapping("/dependents/{dependentId}")
	public void removeDependentById(final @PathVariable("dependentId") Long dependentId) {
		dependentService.delete(dependentId);
	}	
}
