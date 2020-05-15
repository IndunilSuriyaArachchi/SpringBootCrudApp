package com.practice.methods;

import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.practice.customer.CustomerwebappApplication;

@SpringBootTest(classes = CustomerwebappApplication.class)
public class TestControllerWithMockMvc extends AbstractTestNGSpringContextTests {
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	
	private String jsonBody;
	private String customerid;

	@BeforeClass
	public void setup() {
		System.out.println("Im in setup");
		jsonBody = "{\"name\":\"mock1001\",\"salary\":\"123\",\"status\":\"Y\",\"age\":\"23\"}";
		customerid=null;
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
    public void saveCustomer() throws Exception {

        MvcResult result =  mockMvc.perform(post("/customer/save")
                .content(jsonBody)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
        		.andExpect(status().isOk())
        		.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
        		.andExpect(jsonPath("$.name").value("mock1001"))
        		.andReturn();

        String content = result.getResponse().getContentAsString();	
        
        System.out.println("result content---->"+ content);
        customerid = getCustomerIdFromResponse(content);
       
        System.out.println("Customer save success id---->"+ customerid);

    }
	
	@Test(dependsOnMethods = "saveCustomer", enabled = true) 
    public void updateCustomer() throws Exception {
		
		jsonBody=jsonBody.replace("mock1001", "update_mock1001");

        MvcResult result =  mockMvc.perform(put("/customer/update")
                .content(jsonBody)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
        		.andExpect(status().isOk())
        		.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
        		.andExpect(jsonPath("$.name").value("update_mock1001"))
        		.andReturn();

        String content = result.getResponse().getContentAsString();	
        
        System.out.println("result content---->"+ content);
        customerid = getCustomerIdFromResponse(content);
       
        System.out.println("Customer update success id---->"+ customerid);

    }

	@Test(dependsOnMethods = "updateCustomer", enabled = true) 
	public void getCustomerbyId() throws Exception {
		mockMvc.perform(get("/customer/filter/"+customerid))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("[0].name").value("update_mock1001"))
				.andExpect(jsonPath("[0].salary").value(123))
				.andExpect(jsonPath("[0].status").value("Y"))
				.andExpect(jsonPath("[0].age").value(23));
		
		System.out.println("getCustomerbyId---->"+ customerid);

	}
	
	
	@Test(dependsOnMethods = "getCustomerbyId", enabled = true) 
	public void deletecustomer() throws Exception {
		MvcResult result =  mockMvc.perform(delete("/customer/delete/"+customerid)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				//.andExpect(content().contentType("application/json;charset=UTF-8"))  >> Content type = text/plain;charset=UTF-8
				.andExpect(jsonPath("$.status").value("SUCCESS"))
				.andReturn();
				
		String content = result.getResponse().getContentAsString();	
        
        System.out.println("result content---->"+ content);
		System.out.println("Customer delete success id---->"+ customerid);

	}
	
	public String getCustomerIdFromResponse(String json) {
		JSONParser parser = new JSONParser();
        JSONObject jsonResponseObject = new JSONObject();
		
        Object obj = new Object();
        try {
            obj = parser.parse(json);
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        jsonResponseObject = (JSONObject) obj;
        String id = jsonResponseObject.get("id").toString();
        return id;
	}

}
