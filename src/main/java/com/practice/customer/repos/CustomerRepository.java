package com.practice.customer.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.customer.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
