package com.practice.methods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.practice.customer.CustomerwebappApplication;
import com.practice.customer.entities.Customer;
import com.practice.customer.service.CustomerService;

@SpringBootTest(classes = CustomerwebappApplication.class)
public class TestNgServiceCallTest extends AbstractTestNGSpringContextTests {
	
	@Autowired
	CustomerService service;
	
	private Customer customer;
	
	private Customer saveCustomer;
	
	@BeforeClass
	public void setup() {
		System.out.println("I am Testing Service");
		customer=new Customer();
		customer.setName("testng5");
		customer.setAge(52);
		customer.setSalary(232.4);
		
	}
	
	
	@BeforeMethod
	public void setCustomerStatus() {
		System.out.println("I am Testing before method");
		customer.setStatus("Y");
	}
	
	@Test
	public void saveCustomer() {
			
		try {
			saveCustomer = service.saveCustomer(customer);
			
			
			Assert.assertEquals(saveCustomer.getName(),"testng5");
			
			System.out.println("saveCustomer Success");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@AfterMethod
	public void readAfterSave() {
		System.out.println("saveCustomerId:" + saveCustomer.getId());
	}
	
	@AfterClass
	public void tearDown() {
		System.out.println("I am in tearDown2");
	}

}
