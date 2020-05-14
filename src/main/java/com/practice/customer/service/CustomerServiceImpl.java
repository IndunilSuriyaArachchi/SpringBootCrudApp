package com.practice.customer.service;

import java.util.ArrayList;
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
	public Customer saveCustomer(Customer customer) throws Exception{
		customer.setStatus("Y");
		return repository.save(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer)throws Exception {
		customer.setStatus("Y");
		return repository.save(customer);
	}

	@Override
	public List<Customer> getCustomerById(int id)throws Exception {
		
		if(id==0) {
			return repository.findAll();
		}else {
			
			List<Customer> customerList= new ArrayList<Customer>();
			customerList.add(repository.findOne(id));
			return customerList;
		}
		
	}

	@Override
	public List<Customer> getAllCustomers()throws Exception {
		return repository.findAll();
	}
	
	@Override
	public void deleteCustomer(int id) throws Exception{
		Customer customer=new Customer();
		customer.setId(id);
		repository.delete(customer);
	}
	
	@Override
	public Customer getSingleCustomerById(int id) throws Exception{
		return repository.findOne(id);
	}

	public CustomerRepository getRepository() {
		return repository;
	}

	public void setRepository(CustomerRepository repository) {
		this.repository = repository;
	}

	

	
	
	

}
