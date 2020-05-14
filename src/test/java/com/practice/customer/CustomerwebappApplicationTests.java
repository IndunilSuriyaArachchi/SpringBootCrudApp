package com.practice.customer;


import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.practice.customer.controller.CustomerController;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CustomerwebappApplicationTests {

	@Autowired
	private MockMvc mockMvc= MockMvcBuilders.standaloneSetup(new CustomerController()).build();
	
	
	//Customer mockCustomer= new Customer(5, "indunil", 10, 154.5, "Y");
	
	@Test
	public void sample() throws Exception {
		mockMvc.perform(get("/hello")
		.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
        //.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("SUCCESS"));
	}

	/*@Test
	public void testGetAllUserApi() throws Exception {
		mockMvc.perform(get("/cutomer/filter/{id}", 0)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.*").isNotEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("$.*.id").isNotEmpty());

	}*/
	
	/*@Test
	void getAllCustomers() throws Exception {
		
		//Mockito.when(service.getSingleCustomerById(i)).thenReturn(mockCustomer);
		
		RequestBuilder requestBuilder= MockMvcRequestBuilders.get("/customer/delete/10").accept(MediaType.APPLICATION_JSON);

		MvcResult result=mockMvc.perform(requestBuilder).andReturn();
		
		System.out.println("ddddd==="+result.getResponse());
		
		String expected="{id: 0,name: string,age: 133,  salary: 154, status: string}";
		
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(),false);
		
	}*/

}
