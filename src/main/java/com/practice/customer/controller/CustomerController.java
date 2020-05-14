package com.practice.customer.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	
	private JSONObject response = null;
	
	
	@RequestMapping(method=RequestMethod.POST, value= "/customer/save")
	public ResponseEntity<String> saveCustomer(@RequestBody Customer customer) {
		response = new JSONObject();
		try {
			service.saveCustomer(customer);
			response.accumulate("status", "SUCCESS");
		}catch (Exception e) {
			response.accumulate("status", "ERROR");
			e.printStackTrace();
		}
		return ResponseEntity.ok(response.toString());
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/customer/filter/{id}")
	public List<Customer> filterCustomerById(@PathVariable int id) {
		try {
			return service.getCustomerById(id);
		} catch (Exception e) {
			e.printStackTrace();
			
			return new ArrayList<Customer>();
		}
	}
	
	@RequestMapping(method=RequestMethod.PUT, value= "/customer/update")
	public ResponseEntity<String> updateCustomer(@RequestBody Customer customer) {
		response = new JSONObject();
		try {
			service.updateCustomer(customer);
			response.accumulate("status", "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			response.accumulate("status", "ERROR");
		}
		return ResponseEntity.ok(response.toString());
	}

	@RequestMapping(method=RequestMethod.DELETE, value= "/customer/delete/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable int id) {
		response = new JSONObject();
		try {
			service.deleteCustomer(id);
			response.accumulate("status", "SUCCESS");
		} catch (Exception e) {
			response.accumulate("status", "ERROR");
			e.printStackTrace();
		}
		return ResponseEntity.ok(response.toString());
		
		
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/hello")
	public ResponseEntity<String> sample() {
		response = new JSONObject();
		response.accumulate("status", "SUCCESS");
		return ResponseEntity.ok(response.toString());
	}

}
