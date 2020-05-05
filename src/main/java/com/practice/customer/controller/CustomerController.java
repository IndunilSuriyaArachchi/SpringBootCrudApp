package com.practice.customer.controller;

import java.util.List;
import java.util.Map;

import javax.swing.text.DefaultEditorKit.CutAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.practice.customer.entities.Customer;
import com.practice.customer.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService service;
	
	
	@RequestMapping(method=RequestMethod.POST, value= "/customer/save")
	public Customer saveCustomer(@RequestBody Customer customer, ModelMap modelMap) {
		customer.setStatus("Y");
		Customer customerSaved=service.saveCustomer(customer);
		//modelMap.addAttribute("status", customerSaved!=null? "SUCCESS" : "FAILURE");
		return customerSaved;
	}
	
	@RequestMapping("/customer/filter/{id}")
	public Customer filterCustomerById(@PathVariable int id) {
		return service.getCustomerById(id);
	}
	
	@RequestMapping("/customer/filter/0")
	public List<Customer> filterCustomerByIdAll() {
		return service.getAllCustomers();
	}
	
	@RequestMapping(method=RequestMethod.POST, value= "/customer/update")
	public Map updateCustomer(@RequestBody Customer customer, ModelMap modelMap) {
		customer.setStatus("Y");
		Customer customerSaved=service.updateCustomer(customer);
		modelMap.addAttribute("status", customerSaved!=null? "SUCCESS" : "FAILURE");
		return modelMap;
	}

	@RequestMapping(method=RequestMethod.POST, value= "/customer/delete")
	public Customer deleteCustomer(@RequestBody Customer customer, ModelMap modelMap) {
		int customerid=customer.getId();
		service.deleteCustomer(customer);
		Customer customerDeleted=service.getCustomerById(customerid);
		modelMap.addAttribute("status", customerDeleted==null? "SUCCESS" : "FAILURE");
		return customer;
		
		
	}

}
