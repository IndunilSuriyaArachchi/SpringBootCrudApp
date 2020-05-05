package com.practice.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.customer.entities.Customer;
import com.practice.customer.repos.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository repository;
	
	@Override
	public Customer saveCustomer(Customer customer) {
		return repository.save(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		return repository.save(customer);
	}

	@Override
	public Customer getCustomerById(int id) {
		return repository.findOne(id);
	}

	@Override
	public List<Customer> getAllCustomers() {
		return repository.findAll();
	}
	
	@Override
	public void deleteCustomer(Customer customer) {
		repository.delete(customer);
	}

	public CustomerRepository getRepository() {
		return repository;
	}

	public void setRepository(CustomerRepository repository) {
		this.repository = repository;
	}

	
	
	

}
