package com.practice.customer.service;

import java.util.List;

import com.practice.customer.entities.Customer;

public interface CustomerService {

	Customer saveCustomer(Customer customer) throws Exception;
	
	Customer updateCustomer(Customer customer)throws Exception;
	
	List<Customer> getCustomerById(int id)throws Exception;
	
	Customer getSingleCustomerById(int id)throws Exception;
	
	List<Customer> getAllCustomers()throws Exception;
	
	void deleteCustomer(int id)throws Exception;
}
