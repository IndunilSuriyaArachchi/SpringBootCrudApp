package com.practice.customer.service;

import java.util.List;

import com.practice.customer.entities.Customer;

public interface CustomerService {

	Customer saveCustomer(Customer customer);
	
	Customer updateCustomer(Customer customer);
	
	Customer getCustomerById(int id);
	
	List<Customer> getAllCustomers();
	
	void deleteCustomer(Customer customer);
}
