package com.rama.entity;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Student {

	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE, 
			generator = "EMPLOYEE_SEQUENCE"
			)
	@SequenceGenerator(
			name="EMPLOYEE_SEQUENCE", 
			sequenceName = "EMPLOYEE_SEQUENCE", 
			allocationSize=1)
	private Integer id;
	
	private String name;
	@Column(name = "email", unique =true)
	private String email;
	
	private LocalDate dob;
	
	@Transient
	private int age;
		
	public Integer getAge()
	{
		return Period.between(this.dob, LocalDate.now()).getYears();
	}
	
}
