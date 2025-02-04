package com.javapersistence.springdatajpa.repositories;

import com.javapersistence.springdatajpa.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	@Override
	Customer getById(Long id);

	@Override
	List<Customer> findAll();

	// find by username and surname and city
}
