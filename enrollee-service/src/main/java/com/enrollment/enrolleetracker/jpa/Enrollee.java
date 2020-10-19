package com.enrollment.enrolleetracker.jpa;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Singular;

/**
 * @author waynes
 *
 *       Purpose:  Class for persisting Enrollees.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"dependents"})
public class Enrollee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	@NotBlank(message = "Name is required")		
	private String name;
	
	
	@Column(nullable = false)
	@NotNull(message = "Date of birth is required")		
	private LocalDate birthDate;
	
	@Column
	private boolean active;
	
	@OneToMany(mappedBy = "enrollee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Singular
	@JsonIgnore
	private Set<Dependent> dependents;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Enrollee [name=" + name + ", birthDate=" + birthDate + ", status=" + active + "]";
	}	
}
