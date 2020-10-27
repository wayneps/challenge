package com.enrollment.enrolleetracker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import com.enrollment.enrolleetracker.jpa.Dependent;
import com.enrollment.enrolleetracker.jpa.Enrollee;
import com.enrollment.enrolleetracker.jpa.Status;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author waynes
 *
 *       Purpose:  Provides role based global security. 
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes=EnrolleeTrackerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EnrolleeTrackerEntityTest {

	private static final Logger log = LoggerFactory
			.getLogger(EnrolleeTrackerEntityTest.class);
	
	@LocalServerPort
	private int port;
	
	final String host = "http://localhost:";
	final String rootResource = "/apiv1";	
	final String enrolleeResource = rootResource + "/enrollees";	
	final String dependentResource = rootResource + "/dependents";
	
	
	@Autowired
	private TestRestTemplate template;
	
	
	private Enrollee addFulllEnrollee() {
		Enrollee enrollee = addEnrollee("name", LocalDate.now().minusYears(30), true);
		addDependent(enrollee.getId(), "name", LocalDate.now().minusYears(10));
		addDependent(enrollee.getId(), "name", LocalDate.now().minusYears(11));
		return enrollee;
	}
	
	private Dependent addDependent(final Long enrolleeId, final String name, final LocalDate dateOfBirth) {
		Dependent dependent= new Dependent();
		dependent.setName(name);;
		dependent.setDateOfBirth(dateOfBirth.minusYears(67));
		String url = host + port + enrolleeResource + "/" + enrolleeId+"/dependent";
		log.info(url);
		ResponseEntity<Dependent> responseEntity = template.withBasicAuth(
				  "admin1", "password1").postForEntity(url, dependent, Dependent.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		return responseEntity.getBody();
	}

	private Enrollee addEnrollee(final String name, final LocalDate dateOfBirth, final boolean active) {
		
		Enrollee enrollee = new Enrollee();
		enrollee.setName(name);
		enrollee.setDateOfBirth(dateOfBirth.minusYears(67));
		enrollee.setActive(active);		
		String url = host + port + enrolleeResource;
		log.info(url);
		ResponseEntity<Enrollee> responseEntity = template.withBasicAuth(
				  "admin1", "password1").postForEntity(url, enrollee, Enrollee.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		return responseEntity.getBody();
	}
		
	@Test
	public void testAddEnrollee() {
		String name = "name";
		LocalDate dateOfBirth = LocalDate.now().minusYears(14);
		boolean active = true;
		Enrollee enrollee = addEnrollee(name, dateOfBirth, active);
		assertNotNull(enrollee.getId());
		assertEquals(name, enrollee.getName());
		assertEquals(dateOfBirth, enrollee.getDateOfBirth().plusYears(67));
		assertEquals(active, enrollee.getActive());
	}
	
	@Test
	public void testRetrieveEnrollees() {
		addFulllEnrollee();
		String url = host + port + enrolleeResource;
		log.info(url);
		ResponseEntity<Enrollee[]> responseEntity = template.withBasicAuth(
				  "admin1", "password1").getForEntity(url, Enrollee[].class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertNotNull(responseEntity.getBody()[0]);
	}
	
	@Test
	public void testAddRetrieveRemoveEnrollee() {
		
		String name = "name";
		LocalDate dateOfBirth = LocalDate.now().minusYears(35);
		boolean active = true;
		
		Enrollee enrollee = addEnrollee(name, dateOfBirth, active);
		assertNotNull(enrollee.getId());
		String url = enrolleeResource + "/" + enrollee.getId();
		log.info(url);
		ResponseEntity<Enrollee> responseEntity = template.withBasicAuth(
				  "admin1", "password1").getForEntity(url, Enrollee.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		enrollee = responseEntity.getBody();
		assertNotNull(enrollee.getId());
		assertEquals(name, enrollee.getName());
		assertEquals(active, enrollee.getActive());
		assertEquals(dateOfBirth, enrollee.getDateOfBirth().plusYears(67));
		
		template.withBasicAuth(
				  "admin1", "password1").delete(url);
		
		responseEntity = template.withBasicAuth(
				  "admin1", "password1").getForEntity(url, Enrollee.class);
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}
	
	@WithMockUser(username = "user1", password = "password1")	
	@Test
	public void testNoDependent() {
		String url = host + port + dependentResource + "/1";
		log.info(url);
		ResponseEntity<Dependent> responseEntity = template.withBasicAuth(
				  "admin1", "password1").getForEntity(url, Dependent.class);
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}
	
	@Test
	public void testAddDependentNoFirstName() {
		Enrollee enrollee = addEnrollee("name", LocalDate.now().minusYears(35), true);
		assertNotNull(enrollee.getId());
		Dependent dependent= new Dependent();
		dependent.setName(null);
		dependent.setDateOfBirth(LocalDate.now().minusYears(14));
		String url = host + port + enrolleeResource + "/" + enrollee.getId() + "/dependent";
		log.info(url);
		ResponseEntity<Dependent> responseEntity = template.withBasicAuth(
				  "admin1", "password1").postForEntity(url, dependent, Dependent.class);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}
	@Test
	public void testNoDependentForEnrollee() {
		String url = host + port + enrolleeResource + "/3/dependents";
		log.info(url);
		ResponseEntity<Dependent[]> responseEntity = template.withBasicAuth(
				  "admin1", "password1").getForEntity(url, Dependent[].class);
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}
	
	@Test
	public void testRetrieveDependent() {
		Enrollee enrollee = addEnrollee("first name ", LocalDate.now().minusYears(44), true);
		Dependent dependent = addDependent(enrollee.getId(), "last name", LocalDate.now().minusYears(14));
		String url = host + port + dependentResource + "/" + dependent.getId();
		log.info(url);
		ResponseEntity<Dependent> responseEntity = template.withBasicAuth(
				  "admin1", "password1").getForEntity(url, Dependent.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertNotNull(responseEntity.getBody());
	}
	
	@Test
	public void testRetrieveDependents() {
		addFulllEnrollee();
		String url =  host + port + dependentResource;
		log.info(url);
		ResponseEntity<Dependent[]> responseEntity = template.withBasicAuth(
				  "admin1", "password1").getForEntity(url, Dependent[].class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	
	@Test
	public void testRetrieveDependentsForEnrollee() {
		Enrollee enrollee = addFulllEnrollee();
		String url =  host + port + enrolleeResource + "/" + enrollee.getId()+"/dependents";
		log.info(url);
		ResponseEntity<Dependent[]> responseEntity = template.withBasicAuth(
				  "admin1", "password1").getForEntity(url, Dependent[].class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(2, responseEntity.getBody().length);
	}
	
	@Test
	public void testAddFulllEnrolleeRemoveDependent() {
		
		Enrollee enrollee = addEnrollee("first name ", LocalDate.now().minusYears(35), true);
		assertNotNull(enrollee.getId());
		
		String name = "name";
		String lastName = "last name";
		LocalDate dateOfBirth = LocalDate.now().minusYears(14);
		Dependent dependent = addDependent(enrollee.getId(), name, dateOfBirth);
		assertNotNull(dependent.getId());
		assertEquals(name, dependent.getName());
		assertEquals(dateOfBirth, dependent.getDateOfBirth().plusYears(67));
		log.info(enrollee.toString());
		String url =  host + port + dependentResource + "/" + dependent.getId();
		log.info(url);
		template.delete(url);
	} 
	
}
