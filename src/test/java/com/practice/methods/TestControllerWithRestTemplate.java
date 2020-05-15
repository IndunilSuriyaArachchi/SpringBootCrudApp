package com.practice.methods;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

public class TestControllerWithRestTemplate {
	
	final static Logger logger = Logger.getLogger(TestControllerWithRestTemplate.class);
	
	private RestTemplate restTemplate;
	private ResponseEntity<String> responseEntity;
	private HttpHeaders headers; 
	private String baseUrl="http://localhost:8080/SpringBootCrudApp/customer/";
	private String customerid;
	private String responseBody;
	
	@BeforeTest
	public void initialize() {
		
		restTemplate=new RestTemplate();
		customerid = "0";
		
	}
	
	@BeforeMethod
	public void initializeMethods() {
		headers = new HttpHeaders();        
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
	}
	
	@Test
	public void saveCustomer() {
		
		String addURI = baseUrl+"save";
          
        logger.info("Add URL :"+addURI);
        String jsonBody = "{\"name\":\"zozo1001\",\"salary\":\"123\",\"status\":\"Y\",\"age\":\"23\"}";
        System.out.println("\n\n" + jsonBody);
        HttpEntity<String> entity = new HttpEntity<String>(jsonBody, headers);
         
        //POST Method to save customer
        responseEntity = restTemplate.postForEntity(addURI, entity, String.class);
        String responseBodyPOST = responseEntity.getBody();
        // Write response to file
        responseBody = responseEntity.getBody().toString();
        System.out.println("responseBody --->" + responseBody);
        // Get ID from the Response object
        customerid = getCustomerIdFromResponse(responseBody);
        System.out.println("customerid is :" + customerid);
        
        // Check if the added Employee is present in the response body.
        
        Assert.assertTrue(responseBody.contains(customerid));

        // Check if the status code is 201
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        logger.info("Customer is Added successfully customerid:"+customerid);
		
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
	
	
	@Test(dependsOnMethods = "saveCustomer", enabled = true) 
    public void  updateCustomer() { 
		
		String updateURI = baseUrl+"update";    
		System.out.println("Add URL :"+updateURI);
           
        String jsonBody = responseBody;
        
        System.out.println("Response body before update :"+jsonBody); 
         
        jsonBody = jsonBody.replace("zozo1001", "update_zozo100");
                  
        HttpEntity<String> entity = new HttpEntity<String>(jsonBody, headers);
         
        //PUT Method to Update the existing Employee
        //NOTE that I have Not used restTemplate.put as it's void and we need response for verification
        responseEntity = restTemplate.exchange(updateURI, HttpMethod.PUT, entity, String.class);
        responseBody = responseEntity.getBody().toString();
        System.out.println("Update Response Body :"+responseBody);          
     
        // Check if the updated Employee is present in the response body.
        Assert.assertTrue(responseBody.contains("update_zozo100"));
         
        // Check if the status code is 200
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
         
        logger.info("Employee Name is Updated successfully customerid:"+customerid);
     
    }
	
	 @Test(dependsOnMethods = "updateCustomer", enabled = true) 
     void getCustomer()  {     
         String getURI = baseUrl + "filter/" + customerid;
         System.out.println("Get URL :"+getURI);

         HttpEntity<String> entity = new HttpEntity<String>(headers); 
          
         //GET Method to Get existing Employee
         responseEntity = restTemplate.getForEntity(getURI,String.class);
          
         responseBody = responseEntity.getBody().toString();
          
         System.out.println("GET Response Body :"+responseBody);
                  
         // Check if the added Employee ID is present in the response body.
         Assert.assertTrue(responseBody.contains("update_zozo100"));
          
         // Check if the status code is 200
         Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
          
         logger.info("Customer is retrieved successfully employeeId:"+customerid);
      
     }   

	 @Test(dependsOnMethods = "getCustomer", enabled = true)
     public void deleteCustomer() {
         String delURI = baseUrl + "delete/"+this.customerid;
         
         System.out.println("Get URL :"+delURI);

         HttpEntity<String> entity = new HttpEntity<String>(headers); 
        
         responseEntity = restTemplate.exchange(delURI, HttpMethod.DELETE, entity, String.class);  
  
         // Check if the status code is 204
         Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);           
          
         responseBody = responseEntity.getBody();
          
         Assert.assertTrue(responseBody.contains("SUCCESS"));
          
         logger.info("Employee is Deleted successfully employeeId:"+customerid);
     }
}
