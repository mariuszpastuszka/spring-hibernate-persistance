package com.javapersistence.springdatajpa.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;
import org.hibernate.validator.constraints.pl.NIP;

import java.time.LocalDate;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
//@Data
//@Value
public class Customer {

	//	UUID id;
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String surname;

	@Min(value = 18)
//	@Min(value = 18, message = "")
	private int age;

	@Past
	private LocalDate userBornDate;

	@Email
	private String email;

	@NIP
	private String nip;

	@Embedded
	private Address address;
}
