package com.enrollment.enrolleetracker.service;

import java.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enrollment.enrolleetracker.jpa.Dependent;
import com.enrollment.enrolleetracker.jpa.DependentRepository;
import com.enrollment.enrolleetracker.jpa.Enrollee;
import com.enrollment.enrolleetracker.jpa.EnrolleeRepository;
import com.enrollment.enrolleetracker.jpa.Status;

/**
 * @author waynes
 *
 *       Purpose:  Provides seed data for demonstration purposes.
 */
@Service
public class SeedDataService {
	
	private static final Logger log = LoggerFactory
			.getLogger(SeedDataService.class);
	
	@Autowired
	private EnrolleeRepository  enrolleeRepository;
	
	@Autowired
	private DependentRepository dependentRepository;
	
	@Transactional
	public void seed() {
		
		
		dependentRepository.deleteAll();
		enrolleeRepository.deleteAll();
		
		Enrollee enrollee1 = new Enrollee();
		enrollee1.setName("Neil Peart");
		enrollee1.setBirthDate(LocalDate.now().minusYears(67));
		enrollee1.setActive(false);		
		enrolleeRepository.save(enrollee1);
		
		Dependent dependent11 = new Dependent();
		dependent11.setName("Selena Peart");
		dependent11.setBirthDate(LocalDate.now().minusYears(67));
		dependent11.setEnrollee(enrollee1);
		dependentRepository.save(dependent11);		
		
		Dependent dependent12 = new Dependent();
		dependent12.setName("Jacqueline Peart");
		dependent12.setBirthDate(LocalDate.now().minusYears(15));
		dependent12.setEnrollee(enrollee1);
		dependentRepository.save(dependent12);
		
		Enrollee enrollee2 = new Enrollee();
		enrollee2.setName("Chadwick Boseman");
		enrollee2.setBirthDate(LocalDate.now().minusYears(43));
		enrollee2.setActive(true);		
		enrolleeRepository.save(enrollee2);
		
		Enrollee enrollee3 = new Enrollee();
		enrollee3.setName("Alex Lifeson");
		enrollee3.setBirthDate(LocalDate.now().minusYears(68));
		enrollee3.setActive(true);		
		enrolleeRepository.save(enrollee3);
		
		Dependent dependent31 = new Dependent();
		dependent31.setName("Adrian Lifeson");
		dependent31.setBirthDate(LocalDate.now().minusYears(17));
		dependent31.setEnrollee(enrollee3);
		dependentRepository.save(dependent31);		
		
		Dependent dependent32 = new Dependent();
		dependent32.setName("Justin Lifeson");
		dependent32.setBirthDate(LocalDate.now().minusYears(11));
		dependent32.setEnrollee(enrollee3);
		dependentRepository.save(dependent32);
		
		
		Enrollee enrollee4 = new Enrollee();
		enrollee4.setName("Geddy Lee");
		enrollee4.setBirthDate(LocalDate.now().minusYears(67));
		enrollee4.setActive(true);		
		enrolleeRepository.save(enrollee4);
		
		Dependent dependent41 = new Dependent();
		dependent41.setName("Kyla Lee");
		dependent41.setBirthDate(LocalDate.now().minusYears(16));
		dependent41.setEnrollee(enrollee4);
		dependentRepository.save(dependent41);		
		
		Dependent dependent42 = new Dependent();
		dependent42.setName("Julian Lee");
		dependent42.setBirthDate(LocalDate.now().minusYears(14));
		dependent42.setEnrollee(enrollee4);
		dependentRepository.save(dependent42);
		
		
		for (Enrollee enrollee : enrolleeRepository.findAll()) {
			log.info(enrollee.toString());
		}
		for (Dependent dependent : dependentRepository.findAll()) {
			log.info(dependent.toString());
		}
	}

}
